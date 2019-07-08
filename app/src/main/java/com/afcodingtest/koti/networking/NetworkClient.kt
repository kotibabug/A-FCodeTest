package com.afcodingtest.koti.networking

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import android.os.StrictMode
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response


object NetworkClient {

    private const val BASE_URL = "https://www.abercrombie.com/"

    fun getAPIEndPoint(): APIEndPoint {
        val client = OkHttpClient.Builder().addInterceptor(SupportInterceptor()).build()
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(APIEndPoint::class.java)
    }

    class SupportInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request?.newBuilder()
                ?.addHeader("User-Agent", System.getProperty("http.agent"))
                ?.build()
            return chain.proceed(request)
        }
    }
}
