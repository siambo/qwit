package com.jonecx.qwit.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jonecx.qwit.ui.views.AccountRoute

object AccountDestination : QwitNavigationDestination {
    override val route = "account_route"
    override val destination = "account_destination"
}

fun NavGraphBuilder.accountGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = AccountDestination.route) {
        AccountRoute(windowSizeClass = windowSizeClass)
    }
}
