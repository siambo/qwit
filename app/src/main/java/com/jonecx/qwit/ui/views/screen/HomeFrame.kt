package com.jonecx.qwit.ui.views.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jonecx.qwit.navigation.QwitNavigation
import com.jonecx.qwit.navigation.Screen
import com.jonecx.qwit.ui.views.BottomNav.QwitBottomNavBar
import com.jonecx.qwit.ui.views.component.qwitTopAppBar
import com.jonecx.qwit.ui.views.qwitFab
import com.jonecx.qwit.util.Analytics
import com.jonecx.qwit.util.debugLabel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.collect

@OptIn(DelicateCoroutinesApi::class)
@ExperimentalAnimationApi
@Composable
fun HomeFrame(userName: String, analytics: Analytics) {

    val navController = rememberAnimatedNavController()

    LaunchedEffect(navController, analytics) {
        navController.currentBackStackEntryFlow.collect {
            analytics.trackScreenView(
                label = it.debugLabel,
                route = it.destination.route,
                arguments = it.arguments
            )
        }
    }

    Scaffold(
        topBar = { qwitTopAppBar(userName) },
        floatingActionButton = {
            qwitFab(onClick = {
            })
        },
        drawerContent = { Text(text = "drawerContent") },
        bottomBar = {
            val currentSelectedItem by navController.currentScreen()
            QwitBottomNavBar(
                selectedScreen = currentSelectedItem,
                onNavigationSelected = {
                    navController.navigate(it.route) {
                        launchSingleTop = true
                        restoreState = true

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
    ) {
        QwitNavigation(navHostController = navController, analytics = analytics)
    }
}

@Stable
@Composable
private fun NavController.currentScreen(): State<Screen> {
    val selectedItem = remember { mutableStateOf<Screen>(Screen.Home) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == Screen.Home.route } ->
                    selectedItem.value =
                        Screen.Home
                destination.hierarchy.any { it.route == Screen.Love.route } ->
                    selectedItem.value =
                        Screen.Love
                destination.hierarchy.any { it.route == Screen.Bookmark.route } ->
                    selectedItem.value =
                        Screen.Bookmark
                destination.hierarchy.any { it.route == Screen.Mail.route } ->
                    selectedItem.value =
                        Screen.Mail
                destination.hierarchy.any { it.route == Screen.Account.route } ->
                    selectedItem.value =
                        Screen.Account
            }
        }

        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}
