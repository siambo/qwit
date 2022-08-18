package com.jonecx.qwit.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jonecx.qwit.ui.views.BookmarkRoute

object BookmarkDestination : QwitNavigationDestination {
    override val route = "bookmark_route"
    override val destination = "bookmark_destination"
}

fun NavGraphBuilder.bookmarkGraph(
    windowSizeClass: WindowSizeClass
) {
    composable(route = BookmarkDestination.route) {
        BookmarkRoute(windowSizeClass = windowSizeClass)
    }
}
