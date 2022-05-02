package com.jonecx.qwit.util

interface Analytics {
    fun trackScreenView(
        label: String,
        route: String?,
        arguments: Any? = null,
    )
}
