package com.afcodingtest.koti.contract

import com.afcodingtest.koti.BaseView
import com.afcodingtest.koti.model.Promotion

interface PromotionsContract {

    interface View : BaseView {
        fun showPromotions(promotions: List<Promotion>)
        fun showDetail(target: String)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun loadPromotions()
        fun showDetail(target: String?)
    }
}