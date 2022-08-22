package com.jonecx.qwit.ui.usecases

import android.net.Uri
import com.jonecx.qwit.BuildConfig
import com.jonecx.qwit.datasource.QwitClient
import com.jonecx.qwit.ui.viewmodel.OauthStep
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthAccessTokenAndSecretReady
import com.jonecx.qwit.ui.viewmodel.SettingsViewModel
import com.jonecx.qwit.util.Result
import com.jonecx.qwit.util.Result.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAccessTokenAndSecretUseCase(
    private val qwitClient: QwitClient,
    private val settingsViewModel: SettingsViewModel
) : FlowUseCase<Uri, OauthStep>(Dispatchers.IO) {

    private val error = Error(Exception("Authentication Failed"))

    override fun execute(parameters: Uri): Flow<Result<OauthStep>> = flow {
        val oauthVerifier = parameters.getQueryParameter(BuildConfig.OAUTH_VERIFIER)
        val oauthToken = parameters.getQueryParameter(BuildConfig.OAUTH_TOKEN)!!
        when (oauthVerifier.isNullOrBlank()) {
            true -> emit(error)
            false -> {
                qwitClient.authService().getAccessTokenAndSecret(oauthVerifier, oauthToken).execute().body()?.let { body ->
                    val oauthStep = OauthAccessTokenAndSecretReady(body.string())
                    settingsViewModel.saveAuthenticationResult(Result.Success(oauthStep))
                    qwitClient.refreshTokens()
                    emit(Result.Success(oauthStep))
                } ?: emit(error)
            }
        }
    }
}
