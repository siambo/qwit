package com.jonecx.qwit.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jonecx.qwit.util.Result.Error
import timber.log.Timber

open class QwitViewModel : ViewModel() {

    protected fun handleError(errorMessage: String = "Unknown error occurred"): Error {
        Timber.e(errorMessage)
        return Error(Exception(errorMessage))
    }

    protected fun handleError(throwable: Throwable): Error {
        Timber.e(throwable.localizedMessage)
        return Error(throwable)
    }
}
