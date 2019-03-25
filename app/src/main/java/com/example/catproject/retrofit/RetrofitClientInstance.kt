package com.example.catproject.retrofit

import com.example.catproject.CATS_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private val client:MyOkhttpClient? = null
    fun getRetrofit() : Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(CATS_BASE_URL)
                    .client(OkHttpClient.Builder().addInterceptor(MyOkhttpClient())
                        .connectTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES)
                        .writeTimeout(5, TimeUnit.MINUTES)
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

}