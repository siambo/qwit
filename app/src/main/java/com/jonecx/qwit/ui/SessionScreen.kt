package com.jonecx.qwit.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.jonecx.qwit.ui.design.component.QwitBottomBar
import com.jonecx.qwit.ui.design.component.QwitNavRail
import com.jonecx.qwit.ui.navigation.QwitNavHost

@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class
)
@Composable
fun SessionScreen(appState: QwitAppState) {
    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            if (appState.isShowBottomBar) {
                QwitBottomBar(destinations = appState.mainDestinations, onNavigateTo = appState::navigate, currentDestination = appState.currentDestination)
            }
        }
    ) { padding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            if (appState.isShowNavRail) {
                QwitNavRail(destinations = appState.mainDestinations, onNavigateTo = appState::navigate, currentDestination = appState.currentDestination, modifier = Modifier.safeDrawingPadding())
            }
            QwitNavHost(
                navController = appState.navController,
                onNavigateTo = appState::navigate,
                onBackClick = appState::onBackClick,
                windowSizeClass = appState.windowSizeClass,
                modifier = Modifier
                    .padding(padding)
                    .consumedWindowInsets(padding)
            )
        }
    }
}
