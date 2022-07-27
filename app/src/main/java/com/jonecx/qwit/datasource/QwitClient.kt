package com.jonecx.qwit.datasource

import com.jonecx.qwit.BuildConfig
import com.jonecx.qwit.ui.settings.SettingsDataStore
import com.jonecx.qwit.util.orString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

open class QwitClient(private val dataStore: SettingsDataStore) {
    companion object {
        const val API_URL = "https://${BuildConfig.API_HOST}/"
        const val OAUTH_AUTHENTICATE_URL = "${API_URL}oauth/authorize?oauth_token="
    }

    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    suspend fun refreshTokens() {
        getRetrofit().newBuilder().client(getOkHttpClient(true)).build()
    }

    suspend fun authService(): QwitApiService {
        return getRetrofit().create(QwitApiService::class.java)
    }

    private suspend fun getRetrofit(): Retrofit {
        return when (::retrofit.isInitialized) {
            true -> retrofit
            false -> {
                retrofit = retrofitBuilder().build()
                retrofit
            }
        }
    }

    private suspend fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(getOkHttpClient())
    }

    private suspend fun getOkHttpClient(isRefresh: Boolean = false): OkHttpClient {
        return when (::okHttpClient.isInitialized && !isRefresh) {
            true -> okHttpClient
            else -> {
                val builder = OkHttpClient.Builder()
                setOkHttpClientDefaults(builder)
                okHttpClient = builder.build()
                okHttpClient
            }
        }
    }

    private suspend fun setOkHttpClientDefaults(builder: OkHttpClient.Builder) {
        withContext(Dispatchers.IO) {
            val accessToken = dataStore.oauthToken.first().orString(BuildConfig.ACCESS_TOKEN)
            val accessSecret = dataStore.oauthTokenSecret.first().orString(BuildConfig.ACCESS_SECRET)
            val tokens = QwitToken(
                BuildConfig.CONSUMER_KEY,
                BuildConfig.CONSUMER_SECRET,
                accessToken,
                accessSecret
            )
            builder.addInterceptor(QwitInterceptor(tokens))
        }
    }
}
