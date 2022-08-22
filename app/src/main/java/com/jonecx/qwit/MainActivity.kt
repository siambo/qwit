package com.jonecx.qwit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.splashscreen.SplashScreen.KeepOnScreenCondition
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jonecx.qwit.ui.QwitApp
import com.jonecx.qwit.ui.viewmodel.LaunchPath.NavigateToSplash
import com.jonecx.qwit.ui.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity(), KeepOnScreenCondition {
    var isKeepSplashScreenOn = true
    private val settingsViewModel: SettingsViewModel by viewModel()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
            .also { it.setKeepOnScreenCondition(this) }
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                settingsViewModel.launchDestination.collect { path ->
                    isKeepSplashScreenOn = path == NavigateToSplash
                    setContent {
                        QwitApp(calculateWindowSizeClass(this@MainActivity), launchPath = path)
                    }
                }
            }
        }
    }

    override fun shouldKeepOnScreen(): Boolean {
        return isKeepSplashScreenOn
    }
}
