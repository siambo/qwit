package com.jonecx.qwit.datasource

import com.jonecx.qwit.model.UserInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface QwitApiService {

    @FormUrlEncoded
    @POST("oauth/request_token")
    fun getRequestToken(@Field(value = "oauth_callback", encoded = true) oauthCallback: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("/oauth/access_token")
    fun getAccessTokenAndSecret(@Field(value = "oauth_verifier") oauthVerifier: String, @Field(value = "oauth_token") oauthToken: String): Call<ResponseBody>

    @GET("1.1/account/verify_credentials.json")
    suspend fun getAccountCredentials(): UserInfo
}
