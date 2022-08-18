package com.jonecx.qwit.ui.navigation

import com.jonecx.qwit.ui.design.icon.Icon

data class MainDestination(
    override val route: String,
    override val destination: String,
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
) : QwitNavigationDestination
