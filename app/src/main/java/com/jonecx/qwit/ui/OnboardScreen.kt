package com.jonecx.qwit.ui

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jonecx.qwit.R.drawable
import com.jonecx.qwit.R.string
import com.jonecx.qwit.ui.design.image.Image
import com.jonecx.qwit.ui.viewmodel.LaunchPath.NavigateToSession
import com.jonecx.qwit.ui.viewmodel.LoginViewModel
import com.jonecx.qwit.ui.viewmodel.OauthStep
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthAccessTokenAndSecretReady
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthCallbackReady
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthTokenReady
import com.jonecx.qwit.util.Result
import com.jonecx.qwit.util.Result.Error
import com.jonecx.qwit.util.Result.Loading
import com.jonecx.qwit.util.Result.Success
import com.jonecx.qwit.util.data
import com.jonecx.qwit.util.isAuthCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun OnboardScreen(
    appState: QwitAppState
) {

    QwitGradientBackground {
        SetStage(appState)
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun SetStage(appState: QwitAppState) {
    BoxWithConstraints(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical)
        )
    ) {
        val loginViewModel = getViewModel<LoginViewModel>()

        val uiState by mutableStateOf(loginViewModel.requestTokenState.collectAsStateWithLifecycle())

        when (uiState.value) {
            is Success -> {
                when (val oauthStep = uiState.value.data) {
                    is OauthTokenReady -> Authenticate(
                        authenticateUrl = oauthStep.url,
                        uiState = uiState as MutableState<Result<OauthStep>>
                    )
                    is OauthCallbackReady -> {
                        loginViewModel.getAccessTokenAndSecret(oauthStep.uri)
                            .collectAsStateWithLifecycle().value.let { oauthDetails ->
                                if (oauthDetails.data is OauthAccessTokenAndSecretReady)
                                    appState.onboardState = NavigateToSession
                            }
                    }
                    else -> ErrorView()
                }
            }
            is Loading -> StartingScreen()
            is Error -> ErrorView()
        }
    }
}

@Composable
private fun Authenticate(authenticateUrl: String, uiState: MutableState<Result<OauthStep>>) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    var isAuthScheme = false
                    request?.url?.let { callbackUri ->
                        isAuthScheme = callbackUri.isAuthCallback()
                        if (isAuthScheme) {
                            uiState.value = Success(OauthCallbackReady(callbackUri))
                        }
                    }
                    return isAuthScheme
                }
            }
            loadUrl(authenticateUrl)
        }
    })
}

@Composable
private fun ErrorView() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Image(drawableResId = drawable.ic_cloud_error, size = 120.dp, contentDescription = string.qwit_error_cloud)
    }
}
