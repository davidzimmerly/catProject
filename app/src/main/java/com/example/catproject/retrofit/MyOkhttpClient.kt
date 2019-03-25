package com.example.catproject.retrofit

import com.example.catproject.CATS_BASE_URL
import com.example.catproject.CAT_API_KEY
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyOkhttpClient : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        //intercepts our request so we can add some data to it before it reaches the server
        val onGoingRequest: Request.Builder = chain.request().newBuilder()
        onGoingRequest.addHeader("x-api-key", CAT_API_KEY)
        return chain.proceed(onGoingRequest.build())
    }
}