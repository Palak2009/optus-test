package com.optus.codetest.network

import com.optus.codetest.models.Photo
import com.optus.codetest.models.User
import com.optus.codetest.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

object Factory {

    private val service: Network by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.URL_BASE)
            .build()

        retrofit.create(Network::class.java)
    }

    fun getNetworkService() = service

    interface Network {
        @GET(Constants.URL_USERS)
        suspend fun getUsers(): List<User>

        @GET(Constants.URL_PHOTOS)
        suspend fun getPhotos(): List<Photo>
    }

}