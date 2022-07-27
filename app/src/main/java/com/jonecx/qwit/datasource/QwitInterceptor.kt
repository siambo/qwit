package com.jonecx.qwit.datasource

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import okio.ByteString
import okio.ByteString.Companion.toByteString
import java.io.IOException
import java.net.URLEncoder
import java.security.GeneralSecurityException
import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class QwitInterceptor(private val oauthKey: QwitToken) : Interceptor {

    companion object {
        private const val OAUTH_CONSUMER_KEY = "oauth_consumer_key"
        private const val OAUTH_NONCE = "oauth_nonce"
        private const val OAUTH_SIGNATURE = "oauth_signature"
        private const val OAUTH_SIGNATURE_METHOD = "oauth_signature_method"
        private const val OAUTH_SIGNATURE_METHOD_VALUE = "HMAC-SHA1"
        private const val OAUTH_TIMESTAMP = "oauth_timestamp"
        private const val OAUTH_TOKEN = "oauth_token"
        private const val OAUTH_VERSION = "oauth_version"
        private const val OAUTH_VERSION_VALUE = "1.0"

        private const val ALGORITHM = "HmacSHA1"

        private val baseKeys = arrayListOf(
            OAUTH_CONSUMER_KEY,
            OAUTH_NONCE,
            OAUTH_SIGNATURE,
            OAUTH_SIGNATURE_METHOD,
            OAUTH_TIMESTAMP,
            OAUTH_TOKEN,
            OAUTH_VERSION
        )
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(signRequest(request))
    }

    @Throws(IOException::class)
    private fun signRequest(request: Request): Request {
        val timestamp = (System.currentTimeMillis() / 1000L).toString()

        // Setup default parameters that will be sent with authorization header
        val parameters = hashMapOf(
            OAUTH_CONSUMER_KEY to oauthKey.consumerKey,
            OAUTH_NONCE to getNonce(),
            OAUTH_SIGNATURE_METHOD to OAUTH_SIGNATURE_METHOD_VALUE,
            OAUTH_TIMESTAMP to timestamp,
            OAUTH_VERSION to OAUTH_VERSION_VALUE,
            OAUTH_TOKEN to oauthKey.accessToken
        )

        // Copy query parameters into param map
        val url = request.url
        for (i in 0 until url.querySize) {
            parameters[url.queryParameterName(i)] = url.queryParameterValue(i) ?: ""
        }

        // Copy form body into param map
        request.body?.let { body ->
            body.asString().split('&')
                .takeIf { it.isNotEmpty() }
                ?.map { it.split('=', limit = 2) }
                ?.filter {
                    (it.size == 2).also { hasTwoParts ->
                        if (!hasTwoParts) throw IllegalStateException("Key with no value: ${it.getOrNull(0)}")
                    }
                }
                ?.associate {
                    val (key, value) = it
                    key to value
                }
                ?.also { parameters.putAll(it) }
        }

        // Create signature and sign the message
        val method = request.method.encodeUtf8()
        val baseUrl = request.url.newBuilder().query(null).build().toString().encodeUtf8()
        val signingKey = "${oauthKey.consumerSecret.encodeUtf8()}&${oauthKey.accessSecret.encodeUtf8()}"
        val params = parameters.encodeForSignature()
        val dataToSign = "$method&$baseUrl&$params"
        parameters[OAUTH_SIGNATURE] = sign(signingKey, dataToSign).encodeUtf8()

        // Build the header format
        val authHeader = "OAuth ${parameters.toHeaderFormat()}"
        return request.newBuilder().addHeader("Authorization", authHeader).build()
    }

    @Throws(GeneralSecurityException::class)
    private fun sign(key: String, data: String): String {
        val secretKey = SecretKeySpec(key.toBytesUtf8(), ALGORITHM)
        val macResult = Mac.getInstance(ALGORITHM).run {
            init(secretKey)
            doFinal(data.toBytesUtf8())
        }
        return ByteString.of(*macResult).base64()
    }

    private fun String.toBytesUtf8() = this.toByteArray()

    private fun HashMap<String, String>.toHeaderFormat() =
        filter { it.key in baseKeys }
            .toList()
            .sortedBy { (key, _) -> key }
            .toMap()
            .map { "${it.key}=\"${it.value}\"" }
            .joinToString(", ")

    private fun HashMap<String, String>.encodeForSignature() =
        toList()
            .sortedBy { (key, _) -> key }
            .toMap()
            .map { "${it.key}=${it.value}" }
            .joinToString("&")
            .encodeUtf8()

    private fun String.encodeUtf8() = URLEncoder.encode(this, "UTF-8").replace("+", "%2B")

    private fun RequestBody.asString() = Buffer().run {
        writeTo(this)
        readUtf8().replace("+", "%2B")
    }

    private fun getNonce(): String {
        return SecureRandom().let {
            val nonce = ByteArray(32)
            it.nextBytes(nonce)
            nonce.toByteString().toString().encodeUtf8()
        }
    }
}
