package com.afcodingtest.koti.presenter

import com.afcodingtest.koti.contract.PromotionsContract
import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.networking.Callback
import com.sample.networking.APIService

class PromotionsPresenter : PromotionsContract.Presenter {

    private var view: PromotionsContract.View

    constructor(view: PromotionsContract.View) {
        this.view = view
        view.setPresenter(this)
    }

    override fun loadPromotions() {
        view.showLoadingIndicator(true)
        APIService.getPromotions(object : Callback<List<Promotion>>() {
            override fun onResponseSuccess(response: List<Promotion>) {
                view.showPromotions(ArrayList(response))
                view.showLoadingIndicator(false)
            }

            override fun onResponseError(error: String) {
                view.showLoadingError(error)
                view.showLoadingIndicator(false)
            }
        })
    }
}