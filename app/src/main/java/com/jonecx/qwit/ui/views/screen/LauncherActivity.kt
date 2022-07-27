package com.jonecx.qwit.ui.views.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jonecx.qwit.ui.viewmodel.LaunchPath.NavigateToMainActivity
import com.jonecx.qwit.ui.viewmodel.LaunchPath.NavigateToOnboarding
import com.jonecx.qwit.ui.viewmodel.SettingsViewModel
import com.jonecx.qwit.util.navigateOneWayTo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settingsViewModel: SettingsViewModel by viewModel()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                settingsViewModel.launchDestination.collect { goto ->
                    when (goto) {
                        is NavigateToMainActivity -> navigateOneWayTo(HomeActivity::class.java)
                        is NavigateToOnboarding -> navigateOneWayTo(OnboardingActivity::class.java)
                    }
                }
            }
        }
    }
}
