package com.jonecx.qwit.ui.usecases

import com.jonecx.qwit.ui.settings.SettingsDataStore
import com.jonecx.qwit.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

open class OnboardingCompletedUseCase(
    private val settings: SettingsDataStore
) : FlowUseCase<Unit, Boolean>(Dispatchers.IO) {
    override fun execute(parameters: Unit): Flow<Result<Boolean>> =
        settings.isNewUser.map { Result.Success(it) }
}
