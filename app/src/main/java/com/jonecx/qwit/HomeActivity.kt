package com.jonecx.qwit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.jonecx.qwit.ui.theme.QwitTheme
import com.jonecx.qwit.util.FirebaseAnalytics
import com.jonecx.qwit.views.screen.HomeFrame
import org.koin.android.ext.android.inject

class HomeActivity : ComponentActivity() {

    private val analytics: FirebaseAnalytics by inject()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QwitTheme {
                HomeFrame(analytics = analytics)
            }
        }
    }
}
