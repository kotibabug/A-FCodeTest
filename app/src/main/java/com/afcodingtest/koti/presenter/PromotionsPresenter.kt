package com.afcodingtest.koti.presenter

import com.afcodingtest.koti.contract.PromotionsContract
import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.networking.APIEndPoint
import com.afcodingtest.koti.utils.ViewNullException
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PromotionsPresenter @Inject constructor(private val apiEndPoint: APIEndPoint) : PromotionsContract.Presenter {

    private var view: PromotionsContract.View? = null
    override fun attachView(view: PromotionsContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun loadPromotions() {
        view?.let {
            it.showLoadingIndicator(true)
            apiEndPoint.getPromotions().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(object : Observer<List<Promotion>> {
                    override fun onComplete() {}

                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(response: List<Promotion>) {
                        it.showPromotions(ArrayList(response))
                        it.showLoadingIndicator(false)
                    }

                    override fun onError(e: Throwable) {
                        if(e.message != null) {
                            it.showLoadingError(e.message!!)
                        }
                        it.showLoadingIndicator(false)
                    }
                })
        }?:run {
            throw ViewNullException()
        }

    }

    override fun showDetail(target: String?) {
        target?.let {
            view?.showDetail(it) ?: throw ViewNullException()
        }
    }

}