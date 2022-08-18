package com.jonecx.qwit.util

class FirebaseAnalytics : Analytics {
    // TODO
    override fun trackScreenView(label: String, route: String?, arguments: Any?) {
        print(label)
        print(route)
        print(arguments.toString())
    }
}
