package com.jonecx.qwit.di

import com.jonecx.qwit.datasource.QwitClient
import com.jonecx.qwit.ui.settings.SettingsDataStore
import com.jonecx.qwit.ui.usecases.GetAccessTokenAndSecretUseCase
import com.jonecx.qwit.ui.usecases.GetUserIdUseCase
import com.jonecx.qwit.ui.usecases.OnboardingCompletedUseCase
import com.jonecx.qwit.ui.usecases.RequestTokenUseCase
import com.jonecx.qwit.ui.viewmodel.LoginViewModel
import com.jonecx.qwit.ui.viewmodel.SettingsViewModel
import com.jonecx.qwit.util.FirebaseAnalytics
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { QwitClient(get()) }
    single { SettingsDataStore(get()) }

    factory { FirebaseAnalytics() }
    factory { OnboardingCompletedUseCase(get()) }
    factory { GetAccessTokenAndSecretUseCase(get(), get()) }
    factory { GetUserIdUseCase(get()) }
    factory { RequestTokenUseCase(get()) }

    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { SettingsViewModel(get(), get()) }
}
