package com.jonecx.qwit.ui.views.screen

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jonecx.qwit.R.drawable
import com.jonecx.qwit.R.string
import com.jonecx.qwit.ui.Image
import com.jonecx.qwit.ui.theme.QwitTheme
import com.jonecx.qwit.ui.viewmodel.LoginViewModel
import com.jonecx.qwit.ui.viewmodel.OauthStep
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthCallbackReady
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthTokenReady
import com.jonecx.qwit.ui.viewmodel.SettingsViewModel
import com.jonecx.qwit.util.Result
import com.jonecx.qwit.util.Result.Error
import com.jonecx.qwit.util.Result.Loading
import com.jonecx.qwit.util.Result.Success
import com.jonecx.qwit.util.data
import com.jonecx.qwit.util.isAuthCallback
import com.jonecx.qwit.util.navigateOneWayTo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    private val settingsViewModel: SettingsViewModel by viewModel()

    private var uiState by mutableStateOf<Result<OauthStep>>(Loading)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.requestToken().collect { result ->
                    uiState = result
                }
            }
        }

        setContent {
            QwitTheme {
                SetStage(uiState)
            }
        }
    }

    private val oauthWebClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            var isAuthScheme = false
            request?.url?.let { callbackUri ->
                isAuthScheme = callbackUri.isAuthCallback()
                if (isAuthScheme) {
                    uiState = Success(OauthCallbackReady(callbackUri))
                    lifecycleScope.launch {
                        loginViewModel.getAccessTokenAndSecret(callbackUri).collect {
                            settingsViewModel.saveAuthenticationResult(it)
                            navigateOneWayTo(HomeActivity::class.java)
                        }
                    }
                }
            }
            return isAuthScheme
        }
    }

    @Composable
    fun SetStage(uiState: Result<*>) {
        when (uiState) {
            is Success -> {
                when (val oauthStep = uiState.data) {
                    is OauthTokenReady -> Authenticate(authenticateUrl = oauthStep.url)
                    is OauthCallbackReady -> StartingView()
                }
            }
            is Loading -> StartingView()
            is Error -> ErrorView()
        }
    }

    @Composable
    fun Authenticate(authenticateUrl: String) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                webViewClient = oauthWebClient
                loadUrl(authenticateUrl)
            }
        })
    }

    @Composable
    fun StartingView() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Image(drawableResId = drawable.ic_qwit_logo, size = 120.dp, contentDescription = string.qwit_logo)
        }
    }

    @Composable
    fun ErrorView() {
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

    @Composable
    @Preview
    fun StartingCard() {
        QwitTheme() {
            StartingView()
        }
    }

    @Composable
    @Preview
    fun ErrorCard() {
        QwitTheme() {
            ErrorView()
        }
    }
}
