package com.afcodingtest.koti.presenter

import com.afcodingtest.koti.contract.PromotionsContract
import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.networking.NetworkClient
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PromotionsPresenter : PromotionsContract.Presenter {

    private var view: PromotionsContract.View? = null

    override fun attachView(view: PromotionsContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun loadPromotions() {
        view?.showLoadingIndicator(true)
        NetworkClient.getAPIEndPoint().getPromotions().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(object : Observer<List<Promotion>> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(response: List<Promotion>) {
                    view?.showPromotions(ArrayList(response))
                    view?.showLoadingIndicator(false)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { view?.showLoadingError(it) }
                    view?.showLoadingIndicator(false)
                }
            })
    }

    override fun showDetail(target: String?) {
        target?.let { view?.showDetail(it) }
    }
}