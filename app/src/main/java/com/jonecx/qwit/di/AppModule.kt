package com.jonecx.qwit.di

import com.jonecx.qwit.util.FirebaseAnalytics
import org.koin.dsl.module

val injectedModules = module {
    factory { FirebaseAnalytics() }
}

val injectedViewModelModules = module {
}

val appModule = listOf(injectedModules)
