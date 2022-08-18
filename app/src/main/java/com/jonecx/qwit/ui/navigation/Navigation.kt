package com.jonecx.qwit.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun QwitNavHost(
    navController: NavHostController,
    onNavigateTo: (QwitNavigationDestination, String) -> Unit,
    onBackClick: () -> Unit,
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
    startDestination: String = HomeDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(windowSizeClass)
        bookmarkGraph(windowSizeClass)
        savedGraph(windowSizeClass)
        mailGraph(windowSizeClass)
        accountGraph(windowSizeClass)
    }
}
