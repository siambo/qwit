package com.jonecx.qwit.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.jonecx.qwit.BuildConfig
import com.jonecx.qwit.ui.settings.SettingsDataStore
import com.jonecx.qwit.ui.usecases.OnboardingCompletedUseCase
import com.jonecx.qwit.ui.viewmodel.LaunchPath.NavigateToOnboarding
import com.jonecx.qwit.ui.viewmodel.LaunchPath.NavigateToSession
import com.jonecx.qwit.ui.viewmodel.LaunchPath.NavigateToSplash
import com.jonecx.qwit.ui.viewmodel.OauthStep.OauthAccessTokenAndSecretReady
import com.jonecx.qwit.util.Result
import com.jonecx.qwit.util.data
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SettingsViewModel(
    private val settingsDataStore: SettingsDataStore,
    onboardingCompletedUseCase: OnboardingCompletedUseCase,
) : QwitViewModel() {

    val launchDestination = onboardingCompletedUseCase(Unit).map { result ->
        when (result.data) {
            false -> NavigateToSession
            else -> NavigateToOnboarding
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, NavigateToSplash)

    suspend fun getUserId() = settingsDataStore.userId

    suspend fun isNewUser() = settingsDataStore.isNewUser

    suspend fun getScreenName() = settingsDataStore.screenName

    suspend fun getOauthTokenSecret() = settingsDataStore.oauthTokenSecret

    suspend fun getOauthToken() = settingsDataStore.oauthToken

    suspend fun saveAuthenticationResult(tokenAndSecret: Result<OauthStep>) {
        tokenAndSecret.data?.let {
            if (it is OauthAccessTokenAndSecretReady) {
                val tokenSecretMap = it.url
                    .split("&")
                    .map { it.split("=", limit = 4) }
                    .associate {
                        val (key, value) = it
                        key to value
                    }
                settingsDataStore.apply {
                    setNewUser(false)
                    setUserId(tokenSecretMap[BuildConfig.USER_ID].orEmpty())
                    setScreenName(tokenSecretMap[BuildConfig.SCREEN_NAME].orEmpty())
                    saveOauthToken(tokenSecretMap[BuildConfig.OAUTH_TOKEN].orEmpty())
                    saveOauthTokenSecret(tokenSecretMap[BuildConfig.OAUTH_TOKEN_SECRET].orEmpty())
                }
            }
        }
    }
}

sealed class LaunchPath {
    object NavigateToOnboarding : LaunchPath()
    object NavigateToSession : LaunchPath()
    object NavigateToSplash : LaunchPath()
}
