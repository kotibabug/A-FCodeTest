package com.afcodingtest.koti.contract

import com.afcodingtest.koti.BaseView
import com.afcodingtest.koti.model.Promotion

interface PromotionsContract {

    interface View : BaseView<Presenter> {
        fun showPromotions(promotions: ArrayList<Promotion>)
    }

    interface Presenter {
        fun loadPromotions()
    }
}