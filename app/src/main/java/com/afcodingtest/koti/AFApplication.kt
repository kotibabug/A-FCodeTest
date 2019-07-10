package com.afcodingtest.koti

import android.app.Application
import com.afcodingtest.koti.di.component.AppComponent
import com.afcodingtest.koti.di.component.DaggerAppComponent
import com.afcodingtest.koti.di.module.AppModule

class AFApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}