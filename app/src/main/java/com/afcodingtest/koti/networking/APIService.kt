package com.sample.networking


import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.networking.Callback
import com.afcodingtest.koti.networking.PromotionsCall
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


object APIService {

    const val PROMOTIONS_URL = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json"

    fun getPromotions(callback: Callback<List<Promotion>>) {

        PromotionsCall.getPromotions().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
            .subscribe(object : Observer<List<Promotion>> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(response: List<Promotion>) {
                    callback.onResponseSuccess(response)
                }

                override fun onError(e: Throwable) {
                    callback.onResponseError(e.message ?: "Unknown Error")
                }
            })

    }
}