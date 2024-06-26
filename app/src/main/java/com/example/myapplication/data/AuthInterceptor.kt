package com.example.myapplication.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val ts: String, private val publickey: String, private val hash: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", publickey)
            .addQueryParameter("hash", hash)
            .build()

        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }


}