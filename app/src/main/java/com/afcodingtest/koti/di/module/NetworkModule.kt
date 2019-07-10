package com.afcodingtest.koti.di.module

import com.afcodingtest.koti.networking.APIEndPoint
import com.afcodingtest.koti.networking.SupportInterceptor
import com.afcodingtest.koti.utils.Utils
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val NAMED_VALUE = "NAMED_VALUE"
    }

    @Provides
    @Named(NAMED_VALUE)
    fun provideBaseURL(): String = Utils.BASE_URL

    @Provides
    @Singleton
    fun provideNetworkInterceptor(): Interceptor = SupportInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, @Named(NAMED_VALUE) baseURL: String): Retrofit {

        return Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun provideAPIEndClient(retrofit: Retrofit): APIEndPoint {
        return retrofit.create(APIEndPoint::class.java)
    }
}