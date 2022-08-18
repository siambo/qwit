package com.jonecx.qwit.ui.design.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.jonecx.qwit.ui.design.icon.Icon.DrawableResourceIcon
import com.jonecx.qwit.ui.design.icon.Icon.ImageVectorIcon
import com.jonecx.qwit.ui.navigation.MainDestination

@Composable
fun QwitNavRail(
    destinations: List<MainDestination>,
    onNavigateTo: (MainDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    QwitNavigationRail(modifier = modifier) {
        destinations.forEach { destination ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == destination.route } == true
            QwitNavigationRailItem(
                selected = isSelected,
                onClick = { onNavigateTo(destination) },
                icon = {
                    when (val icon = if (isSelected) destination.selectedIcon else destination.unselectedIcon) {
                        is ImageVectorIcon -> Icon(imageVector = icon.imageVector, contentDescription = null)
                        is DrawableResourceIcon -> Icon(painter = painterResource(id = icon.id), contentDescription = null)
                    }
                },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }
}

@Composable
fun QwitNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    NavigationRail(
        modifier = modifier,
        containerColor = QwitNavigationDefaults.NavigationContainerColor,
        contentColor = QwitNavigationDefaults.navigationContentColor(),
        header = header,
        content = content
    )
}

@Composable
fun QwitNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = QwitNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = QwitNavigationDefaults.navigationContentColor(),
            selectedTextColor = QwitNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = QwitNavigationDefaults.navigationContentColor(),
            indicatorColor = QwitNavigationDefaults.navigationIndicatorColor()
        )
    )
}
