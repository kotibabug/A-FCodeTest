package com.afcodingtest.koti.di.module

import com.afcodingtest.koti.contract.PromotionsContract
import com.afcodingtest.koti.networking.APIEndPoint
import com.afcodingtest.koti.presenter.PromotionsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PromotionsPresenterModule {

    @Provides
    @Singleton
    internal fun getPromotionsPresenter(apiEndPoint: APIEndPoint): PromotionsContract.Presenter {
        return PromotionsPresenter(apiEndPoint)
    }
}