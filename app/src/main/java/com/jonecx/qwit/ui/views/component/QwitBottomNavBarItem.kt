package com.jonecx.qwit.ui.views.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jonecx.qwit.R
import com.jonecx.qwit.navigation.Screen

sealed class QwitBottomNavBarItem(
    val screen: Screen,
    @DrawableRes var icon: Int,
    @DrawableRes val selectedIcon: Int,
    @StringRes var title: Int,
) {
    object Home : QwitBottomNavBarItem(Screen.Home, R.drawable.ic_home_outline, R.drawable.ic_home_filled, R.string.home)
    object Love : QwitBottomNavBarItem(Screen.Love, R.drawable.ic_heart_outline, R.drawable.ic_heart_filled, R.string.love)
    object Bookmark : QwitBottomNavBarItem(Screen.Bookmark, R.drawable.ic_bookmark_outline, R.drawable.ic_bookmark_filled, R.string.bookmark)
    object Mail : QwitBottomNavBarItem(Screen.Mail, R.drawable.ic_email_outline, R.drawable.ic_email_filled, R.string.mail)
    object Account : QwitBottomNavBarItem(Screen.Account, R.drawable.ic_account_outline, R.drawable.ic_account_filled, R.string.account)
}
