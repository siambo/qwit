package com.jonecx.qwit.ui.views.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jonecx.qwit.ui.theme.QwitTheme
import com.jonecx.qwit.ui.viewmodel.LoginViewModel
import com.jonecx.qwit.ui.viewmodel.SettingsViewModel
import com.jonecx.qwit.util.FirebaseAnalytics
import com.jonecx.qwit.util.Result.Success
import com.jonecx.qwit.util.data
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : ComponentActivity() {

    private val analytics: FirebaseAnalytics by inject()
    private val settingsViewModel: SettingsViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()

    var uiState by mutableStateOf("")

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                settingsViewModel.getScreenName().collect {
                    uiState = it
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                loginViewModel.getUserId().collect {
                    if (it is Success)
                        uiState = it.data.name
                    else
                        Toast.makeText(this@HomeActivity, "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
        setContent {
            QwitTheme {
                HomeFrame(uiState, analytics = analytics)
            }
        }
    }
}
