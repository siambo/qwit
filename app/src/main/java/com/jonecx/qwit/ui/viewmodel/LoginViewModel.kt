package com.jonecx.qwit.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.jonecx.qwit.model.User
import com.jonecx.qwit.ui.usecases.GetAccessTokenAndSecretUseCase
import com.jonecx.qwit.ui.usecases.GetUserIdUseCase
import com.jonecx.qwit.ui.usecases.RequestTokenUseCase
import com.jonecx.qwit.util.Result
import com.jonecx.qwit.util.Result.Loading
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class LoginViewModel(
    requestTokenUseCase: RequestTokenUseCase,
    getUserIdUseCase: GetUserIdUseCase,
    private val getAccessTokenAndSecretUseCase: GetAccessTokenAndSecretUseCase,
) : QwitViewModel() {

    val requestTokenState: StateFlow<Result<OauthStep>> =
        requestTokenUseCase(Unit)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Loading)

    fun getAccessTokenAndSecret(uri: Uri): StateFlow<Result<OauthStep>> =
        getAccessTokenAndSecretUseCase(uri)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Loading)

    val userIdState: StateFlow<Result<User>> =
        getUserIdUseCase(Unit)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Loading)
}

sealed class OauthStep {
    data class OauthTokenReady(val url: String) : OauthStep()
    data class OauthAccessTokenAndSecretReady(val url: String) : OauthStep()
    data class OauthCallbackReady(val uri: Uri) : OauthStep()
}
