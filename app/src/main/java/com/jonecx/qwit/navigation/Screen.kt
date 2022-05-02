package com.jonecx.qwit.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Love : Screen("love")
    object Bookmark : Screen("bookmark")
    object Mail : Screen("mail")
    object Account : Screen("account")
}

internal sealed class LeafScreen(
    private val route: String
) {
    object Home : LeafScreen(Screen.Home.route)
    object Love : LeafScreen(Screen.Love.route)
    object Bookmark : LeafScreen(Screen.Bookmark.route)
    object Mail : LeafScreen(Screen.Mail.route)
    object Account : LeafScreen(Screen.Account.route)

    fun createRoute(root: Screen) = "${root.route}/$route"
}
