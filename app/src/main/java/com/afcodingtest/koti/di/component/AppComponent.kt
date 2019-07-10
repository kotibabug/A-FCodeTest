package com.afcodingtest.koti.di.component

import com.afcodingtest.koti.di.module.AppModule
import com.afcodingtest.koti.di.module.NetworkModule
import com.afcodingtest.koti.di.module.PromotionsPresenterModule
import com.afcodingtest.koti.ui.view.PromotionsActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, PromotionsPresenterModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: PromotionsActivity)
}