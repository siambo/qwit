package com.jonecx.qwit.ui.views.BottomNav

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.jonecx.qwit.R
import com.jonecx.qwit.navigation.Screen
import com.jonecx.qwit.ui.views.component.QwitBottomNavBarItem
import com.jonecx.qwit.ui.views.component.QwitIcon

@Composable
fun QwitBottomNavBar(selectedScreen: Screen, onNavigationSelected: (Screen) -> Unit) {
    val qwitBottomNavItems = listOf(
        QwitBottomNavBarItem.Home,
        QwitBottomNavBarItem.Love,
        QwitBottomNavBarItem.Bookmark,
        QwitBottomNavBarItem.Mail,
        QwitBottomNavBarItem.Account
    )

    BottomNavigation(
        modifier = Modifier
            .testTag(R.string.tt_bottom_nav.toString())
    ) {
        qwitBottomNavItems.forEach {
            BottomNavigationItem(
                modifier = Modifier.testTag(it.title.toString()),
                icon = {
                    QwitIcon(
                        iconId = if (selectedScreen == it.screen) it.selectedIcon else it.icon,
                        contentDescId = it.title,
                        tint = if (selectedScreen == it.screen) MaterialTheme.colors.secondary else MaterialTheme.colors.onPrimary,
                    )
                },
                selected = selectedScreen == it.screen,
                onClick = {
                    onNavigationSelected(it.screen)
                }
            )
        }
    }
}
