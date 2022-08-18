package com.jonecx.qwit.ui

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.tracing.trace
import com.jonecx.qwit.R.drawable
import com.jonecx.qwit.R.string
import com.jonecx.qwit.ui.design.icon.Icon.DrawableResourceIcon
import com.jonecx.qwit.ui.navigation.AccountDestination
import com.jonecx.qwit.ui.navigation.BookmarkDestination
import com.jonecx.qwit.ui.navigation.HomeDestination
import com.jonecx.qwit.ui.navigation.MailDestination
import com.jonecx.qwit.ui.navigation.MainDestination
import com.jonecx.qwit.ui.navigation.QwitNavigationDestination
import com.jonecx.qwit.ui.navigation.SavedDestination
import com.jonecx.qwit.ui.viewmodel.LaunchPath

@Composable
fun rememberQwitAppState(
    windowSizeClass: WindowSizeClass,
    launchPath: LaunchPath,
    navController: NavHostController = rememberNavController()
): QwitAppState {
    return remember(navController, windowSizeClass, launchPath) {
        QwitAppState(navController, windowSizeClass, launchPath)
    }
}

@Stable
class QwitAppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass,
    launchPath: LaunchPath
) {

    var onboardState by mutableStateOf(launchPath)

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val isShowBottomBar = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
        windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

    val isShowNavRail = !isShowBottomBar

    /**
     * Top level destinations in the bottom nav and rail nav
     */
    val mainDestinations = listOf(
        MainDestination(
            route = HomeDestination.route,
            destination = HomeDestination.destination,
            selectedIcon = DrawableResourceIcon(drawable.ic_home_filled),
            unselectedIcon = DrawableResourceIcon(drawable.ic_home_outline),
            iconTextId = string.home
        ),
        MainDestination(
            route = SavedDestination.route,
            destination = SavedDestination.destination,
            selectedIcon = DrawableResourceIcon(drawable.ic_heart_filled),
            unselectedIcon = DrawableResourceIcon(drawable.ic_heart_outline),
            iconTextId = string.love
        ),
        MainDestination(
            route = BookmarkDestination.route,
            destination = BookmarkDestination.destination,
            selectedIcon = DrawableResourceIcon(drawable.ic_bookmark_filled),
            unselectedIcon = DrawableResourceIcon(drawable.ic_bookmark_outline),
            iconTextId = string.bookmark
        ),
        MainDestination(
            route = MailDestination.route,
            destination = MailDestination.destination,
            selectedIcon = DrawableResourceIcon(drawable.ic_email_filled),
            unselectedIcon = DrawableResourceIcon(drawable.ic_email_outline),
            iconTextId = string.mail
        ),
        MainDestination(
            route = AccountDestination.route,
            destination = AccountDestination.destination,
            selectedIcon = DrawableResourceIcon(drawable.ic_account_filled),
            unselectedIcon = DrawableResourceIcon(drawable.ic_account_outline),
            iconTextId = string.account
        )
    )

    fun navigate(destination: QwitNavigationDestination, route: String? = null) {
        trace("Navigation: $destination") {
            if (destination is MainDestination) {
                navController.navigate(route ?: destination.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            } else {
                navController.navigate(route ?: destination.route)
            }
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}
