package com.jonecx.qwit.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jonecx.qwit.ui.HomeRoute

object HomeDestination : QwitNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homeGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = HomeDestination.route) {
        HomeRoute(windowSizeClass = windowSizeClass)
    }
}
