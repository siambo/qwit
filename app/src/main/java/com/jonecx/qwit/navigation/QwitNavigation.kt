package com.jonecx.qwit.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.navigation
import com.jonecx.qwit.ui.views.screen.AccountScreen
import com.jonecx.qwit.ui.views.screen.BookmarkScreen
import com.jonecx.qwit.ui.views.screen.LoveScreen
import com.jonecx.qwit.ui.views.screen.MailScreen
import com.jonecx.qwit.util.Analytics
import com.jonecx.qwit.util.composable

@ExperimentalAnimationApi
@Composable
fun QwitNavigation(navHostController: NavHostController, analytics: Analytics) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        addHomeTopLevel(navHostController, analytics)
        addLoveTopLevel(navHostController, analytics)
        addBookmarkTopLevel(navHostController, analytics)
        addMailTopLevel(navHostController, analytics)
        addAccountTopLevel(navHostController, analytics)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addHomeTopLevel(
    navController: NavController,
    analytics: Analytics,
) {
    navigation(
        route = Screen.Home.route,
        startDestination = LeafScreen.Home.createRoute(Screen.Home),
    ) {
        addHome(navController, Screen.Home, analytics)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addLoveTopLevel(
    navController: NavController,
    analytics: Analytics,
) {
    navigation(
        route = Screen.Love.route,
        startDestination = LeafScreen.Love.createRoute(Screen.Love),
    ) {
        addLove(navController, Screen.Love, analytics)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addBookmarkTopLevel(
    navController: NavController,
    analytics: Analytics,
) {
    navigation(
        route = Screen.Bookmark.route,
        startDestination = LeafScreen.Bookmark.createRoute(Screen.Bookmark),
    ) {
        addBookmark(navController, Screen.Bookmark, analytics)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addMailTopLevel(
    navController: NavController,
    analytics: Analytics,
) {
    navigation(
        route = Screen.Mail.route,
        startDestination = LeafScreen.Mail.createRoute(Screen.Mail),
    ) {
        addMail(navController, Screen.Mail, analytics)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addAccountTopLevel(
    navController: NavController,
    analytics: Analytics,
) {
    navigation(
        route = Screen.Account.route,
        startDestination = LeafScreen.Account.createRoute(Screen.Account),
    ) {
        addAccount(navController, Screen.Account, analytics)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addHome(
    navController: NavController,
    root: Screen,
    analytics: Analytics
) {
    composable(LeafScreen.Home.createRoute(root), debugLabel = Screen.Home.route) {
        com.jonecx.qwit.util.HomeScreen()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addLove(
    navController: NavController,
    root: Screen,
    analytics: Analytics
) {
    composable(
        route = LeafScreen.Love.createRoute(root),
        debugLabel = Screen.Love.route
    ) {
        LoveScreen()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addBookmark(
    navController: NavController,
    root: Screen,
    analytics: Analytics
) {
    composable(
        route = LeafScreen.Bookmark.createRoute(root),
        debugLabel = Screen.Bookmark.route
    ) {
        BookmarkScreen()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addMail(
    navController: NavController,
    root: Screen,
    analytics: Analytics
) {
    composable(
        route = LeafScreen.Mail.createRoute(root),
        debugLabel = Screen.Mail.route
    ) {
        MailScreen()
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.addAccount(
    navController: NavController,
    root: Screen,
    analytics: Analytics
) {
    composable(
        route = LeafScreen.Account.createRoute(root),
        debugLabel = Screen.Account.route
    ) {
        AccountScreen()
    }
}
