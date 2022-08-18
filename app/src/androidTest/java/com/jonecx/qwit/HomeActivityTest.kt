package com.jonecx.qwit

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeActivityTest : QwitTest() {

    @Test
    fun testAppContext() {
        assertEquals(appPackageName, appContext.packageName)
        assertEquals(appName, appContext.resources.getString(appContext.applicationInfo.labelRes))
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Test
    fun testHomeFrame() {
        composeTestRule.setContent {
        }

        assertBottomNavClicks(R.string.home, R.string.tt_home_screen_center_image)
        assertBottomNavClicks(R.string.love, R.string.tt_love_screen_center_image)
        assertBottomNavClicks(R.string.bookmark, R.string.tt_bookmark_screen_center_image)
        assertBottomNavClicks(R.string.mail, R.string.tt_mail_screen_center_image)
        assertBottomNavClicks(R.string.account, R.string.tt_account_screen_center_image)

        isViewVisible(R.string.tt_floating_button)
    }

    private fun assertBottomNavClicks(@StringRes bottomNavItemTag: Int, @StringRes centerImageTestTag: Int) {
        isBottomNavVisible()
        clickOnView(bottomNavItemTag)
        isViewVisible(centerImageTestTag)
    }

    private fun isBottomNavVisible() {
        isViewVisible(R.string.tt_bottom_nav)
        isViewVisible(R.string.home)
        isViewVisible(R.string.love)
        isViewVisible(R.string.bookmark)
        isViewVisible(R.string.mail)
        isViewVisible(R.string.account)
    }
}
