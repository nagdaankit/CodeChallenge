package com.codingchallenge.ctw.di

import okhttp3.Interceptor
import okhttp3.Response

class ApiRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("x-api-key", "c1383636572941719442e7a49a7570af")
                .build()
        )
    }
}