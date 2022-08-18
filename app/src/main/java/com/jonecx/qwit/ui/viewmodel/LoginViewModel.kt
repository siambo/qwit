package com.jonecx.qwit.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.jonecx.qwit.BuildConfig
import com.jonecx.qwit.datasource.QwitClient
import com.jonecx.qwit.model.User
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthAccessTokenAndSecretReady
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthTokenReady
import com.jonecx.qwit.util.Result
import com.jonecx.qwit.util.Result.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class LoginViewModel(
    private val qwitClient: QwitClient,
    private val settingsViewModel: SettingsViewModel,
) : QwitViewModel() {

    val requestTokenState: StateFlow<Result<OauthStep>> = flow {
        qwitClient.authService().getRequestToken(BuildConfig.CALLBACK_SCHEME).execute().body()
            ?.let { body ->
                val authenticateUrl = buildAuthenticateUrl(body.string())
                emit(authenticateUrl)
            }
    }
        .catch { e -> emit(handleError(e)) }
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Loading
        )

    fun getAccessTokenAndSecret(uri: Uri) = flow<Result<OauthStep>> {
        val oauthVerifier = uri.getQueryParameter(BuildConfig.OAUTH_VERIFIER)
        val oauthToken = uri.getQueryParameter(BuildConfig.OAUTH_TOKEN)!!
        when (oauthVerifier.isNullOrBlank()) {
            true -> emit(handleError("Authentication failed"))
            false -> {
                qwitClient.authService().getAccessTokenAndSecret(oauthVerifier, oauthToken).execute().body()?.let { body ->
                    val oauthStep = OauthAccessTokenAndSecretReady(body.string())
                    settingsViewModel.saveAuthenticationResult(Result.Success(oauthStep))
                    qwitClient.refreshTokens()
                    emit(Result.Success(oauthStep))
                } ?: emit(handleError("Authentication failed"))
            }
        }
    }
        .catch { e -> emit(handleError(e)) }.flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Loading
        )

    fun getUserId() = flow<Result<User>> {
        qwitClient.authService().getAccountCredentials().let {
            emit(Result.Success(it))
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
}

sealed class OauthStep {
    data class OauthTokenReady(val url: String) : OauthStep()
    data class OauthAccessTokenAndSecretReady(val url: String) : OauthStep()
    data class OauthCallbackReady(val uri: Uri) : OauthStep()
}
