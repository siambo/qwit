package com.jonecx.qwit.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import com.jonecx.qwit.ui.design.theme.QwitTheme
import com.jonecx.qwit.ui.viewmodel.LaunchPath
import com.jonecx.qwit.ui.viewmodel.LaunchPath.NavigateToOnboarding
import com.jonecx.qwit.ui.viewmodel.LaunchPath.NavigateToSession
import com.jonecx.qwit.ui.viewmodel.LaunchPath.NavigateToSplash

@Composable
fun QwitApp(
    windowSizeClass: WindowSizeClass,
    launchPath: LaunchPath,
    appState: QwitAppState = rememberQwitAppState(windowSizeClass, launchPath)
) {
    QwitTheme {
        QwitBackground {
            appState.let {
                when (it.onboardState) {
                    NavigateToSplash -> SplashScreen()
                    NavigateToSession -> SessionScreen(appState = it)
                    NavigateToOnboarding -> OnboardScreen(appState = it)
                }
            }
        }
    }
}
