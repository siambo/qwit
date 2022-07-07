package com.jonecx.qwit.datasource

import com.jonecx.qwit.BuildConfig
import com.jonecx.qwit.ui.settings.SettingsDataStore
import com.slack.eithernet.ApiResultCallAdapterFactory
import com.slack.eithernet.ApiResultConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

open class QwitClient(private val dataStore: SettingsDataStore) {
    companion object {
        private const val API_HOST = "api.twitter.com"
        const val API_URL = "https://$API_HOST/"
        const val OAUTH_AUTHENTICATE_URL = "${API_URL}oauth/authorize?oauth_token="
    }

    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    suspend fun refresh() {
        getRetrofit() // not complete yet
    }

    suspend fun authService(): QwitApi {
        return getRetrofit().create(QwitApi::class.java)
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
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(ApiResultConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResultCallAdapterFactory)
            .client(getOkHttpClient())
    }

    private suspend fun getOkHttpClient(): OkHttpClient {
        return when (::okHttpClient.isInitialized) {
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
            val accessToken = dataStore.oauthToken.firstOrNull().orEmpty()
            val accessSecret = dataStore.oauthTokenSecret.firstOrNull().orEmpty()
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
