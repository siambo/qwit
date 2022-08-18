package com.jonecx.qwit.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jonecx.qwit.ui.SavedRoute

object SavedDestination : QwitNavigationDestination {
    override val route = "saved_route"
    override val destination = "saved_destination"
}

fun NavGraphBuilder.savedGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = SavedDestination.route) {
        SavedRoute(windowSizeClass = windowSizeClass)
    }
}
