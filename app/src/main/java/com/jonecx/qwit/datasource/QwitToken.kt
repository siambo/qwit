package com.jonecx.qwit.datasource

import java.security.SecureRandom
import java.time.Clock

data class QwitToken(
    val consumerKey: String,
    val consumerSecret: String,
    val accessToken: String,
    val accessSecret: String,
    val random: SecureRandom = SecureRandom(),
    val clock: Clock = Clock.systemDefaultZone()
)
