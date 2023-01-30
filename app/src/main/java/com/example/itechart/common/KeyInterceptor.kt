package com.example.itechart.common

import com.example.itechart.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class KeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("X-ListenAPI-Key", BuildConfig.API_KEY)
        return chain.proceed(builder.build())
    }
}