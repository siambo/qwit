package com.jonecx.qwit.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jonecx.qwit.ui.MailRoute

object MailDestination : QwitNavigationDestination {
    override val route = "mail_route"
    override val destination = "mail_destination"
}

fun NavGraphBuilder.mailGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = MailDestination.route) {
        MailRoute(windowSizeClass = windowSizeClass)
    }
}
