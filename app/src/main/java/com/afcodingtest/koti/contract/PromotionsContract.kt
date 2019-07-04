package com.afcodingtest.koti.contract

import com.afcodingtest.koti.BaseView
import com.afcodingtest.koti.model.Promotion

interface PromotionsContract {

    interface View : BaseView<Presenter> {
        fun showPromotions(promotions: ArrayList<Promotion>)
        fun showDetail(target: String)
    }

    interface Presenter {
        fun loadPromotions()
        fun showDetail(target: String)
    }
}