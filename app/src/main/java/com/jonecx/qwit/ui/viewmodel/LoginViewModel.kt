package com.jonecx.qwit.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.jonecx.qwit.BuildConfig
import com.jonecx.qwit.datasource.QwitClient
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthAccessTokenAndSecretReady
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthTokenReady
import com.jonecx.qwit.util.Result
import com.jonecx.qwit.util.Result.Error
import com.slack.eithernet.ApiResult
import com.slack.eithernet.ApiResult.Failure.ApiFailure
import com.slack.eithernet.ApiResult.Failure.HttpFailure
import com.slack.eithernet.ApiResult.Failure.NetworkFailure
import com.slack.eithernet.ApiResult.Failure.UnknownFailure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class LoginViewModel(
    private val qwitClient: QwitClient,
    private val settingsViewModel: SettingsViewModel
) : ViewModel() {

    fun requestToken() = flow {
        qwitClient.authService().getRequestToken(BuildConfig.CALLBACK_SCHEME).execute().body()?.let { body ->
            val authenticateUrl = buildAuthenticateUrl(body.string())
            emit(authenticateUrl)
        } ?: emit(handleError("Unable to get the service url"))
    }.catch { e -> emit(handleError(e)) }.flowOn(Dispatchers.IO)

    fun getAccessTokenAndSecret(uri: Uri) = flow<Result<OauthStep>> {
        val oauthVerifier = uri.getQueryParameter(BuildConfig.OAUTH_VERIFIER)
        val oauthToken = uri.getQueryParameter(BuildConfig.OAUTH_TOKEN)!!
        when (oauthVerifier.isNullOrBlank()) {
            true -> emit(handleError("Authentication failed"))
            false -> {
                qwitClient.authService().getAccessTokenAndSecret(oauthVerifier, oauthToken).execute().body()?.let { body ->
                    val oauthStep = OauthAccessTokenAndSecretReady(body.string())
                    settingsViewModel.saveAuthenticationResult(Result.Success(oauthStep))
                    qwitClient.refresh()
                    emit(Result.Success(oauthStep))
                } ?: emit(handleError("Authentication failed"))
            }
        }
    }.catch { e -> emit(handleError(e)) }.flowOn(Dispatchers.IO)

    fun getUserId() = flow {
        when (val rms = qwitClient.authService().getAccountCredentials()) {
            is ApiResult.Success -> emit(Result.Success(rms.value))
            is ApiResult.Failure -> emit(
                when (rms) {
                    is HttpFailure -> handleError("failed with status code: ${rms.code}")
                    is ApiFailure -> handleError("Api call failed")
                    is NetworkFailure -> handleError(rms.error)
                    is UnknownFailure -> handleError(rms.error)
                }
            )
        }
    }.catch { e -> emit(handleError(e)) }.flowOn(Dispatchers.IO)

    private fun buildAuthenticateUrl(tokens: String): Result<OauthStep> {
        val tokenMaps = tokens.split('&')
            .takeIf { it.isNotEmpty() }
            ?.map { it.split('=', limit = 3) }
            ?.associate {
                val (key, value) = it
                key to value
            }

        val token = tokenMaps?.get(BuildConfig.OAUTH_TOKEN)
        return when (token != null && token.isNotEmpty()) {
            true -> {
                val tokenAndUrl = "${QwitClient.OAUTH_AUTHENTICATE_URL}$token"
                Result.Success(OauthTokenReady(tokenAndUrl))
            }
            else -> handleError("Token not available")
        }
    }

    private fun handleError(errorMessage: String = "Unknown error occurred"): Error {
        Timber.e(errorMessage)
        return Error(Exception(errorMessage))
    }

    private fun handleError(throwable: Throwable): Error {
        Timber.e(throwable.localizedMessage)
        return Error(throwable)
    }
}

sealed class OauthStep {
    data class OauthTokenReady(val url: String) : OauthStep()
    data class OauthAccessTokenAndSecretReady(val url: String) : OauthStep()
    data class OauthCallbackReady(val uri: Uri) : OauthStep()
}
