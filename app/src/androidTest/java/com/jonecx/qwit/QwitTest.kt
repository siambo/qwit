package com.jonecx.qwit

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jonecx.qwit.util.FirebaseAnalytics
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
open class QwitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    protected val appPackageName = "com.jonecx.qwit"
    protected val appName = "Qwit"

    protected val analytics = FirebaseAnalytics()
    protected val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    companion object {
        @BeforeClass
        @JvmStatic
        fun enableAccessibilityChecks() {
            AccessibilityChecks.enable()
                .setRunChecksFromRootView(true)
        }
    }

    @Before
    fun loadModules() {
        loadKoinModules(
            module {
                single { analytics }
            }
        )
    }

    protected fun isViewVisible(@StringRes testTagId: Int) {
        composeTestRule.onNodeWithTag(testTagId.toString()).assertIsDisplayed()
    }

    protected fun clickOnView(@StringRes testTagId: Int) {
        composeTestRule.onNodeWithTag(testTagId.toString()).performClick()
    }
}
