package com.jonecx.qwit.ui.usecases

import com.jonecx.qwit.BuildConfig
import com.jonecx.qwit.datasource.QwitClient
import com.jonecx.qwit.ui.viewmodel.OauthStep
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthTokenReady
import com.jonecx.qwit.util.Result
import com.jonecx.qwit.util.Result.Error
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RequestTokenUseCase(
    private val qwitClient: QwitClient
) : FlowUseCase<Unit, OauthStep>(Dispatchers.IO) {
    override fun execute(parameters: Unit): Flow<Result<OauthStep>> = flow {
        qwitClient.authService().getRequestToken(BuildConfig.CALLBACK_SCHEME).execute().body()
            ?.let { body ->
                val authenticateUrl = buildAuthenticateUrl(body.string())
                emit(authenticateUrl)
            }
    }
}

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
        else -> Error(Exception("Token not available"))
    }
}
