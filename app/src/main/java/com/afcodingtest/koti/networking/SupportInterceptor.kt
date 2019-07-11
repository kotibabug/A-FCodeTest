package com.afcodingtest.koti.networking

import okhttp3.Interceptor
import okhttp3.Response

class SupportInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("User-Agent", System.getProperty("http.agent")).build()
        return chain.proceed(request)
    }
}