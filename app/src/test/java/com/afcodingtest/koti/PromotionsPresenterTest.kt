package com.afcodingtest.koti

import com.afcodingtest.koti.contract.PromotionsContract
import com.afcodingtest.koti.networking.PromotionsCall
import com.afcodingtest.koti.presenter.PromotionsPresenter
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins


class PromotionsPresenterTest {
    private lateinit var presenter: PromotionsPresenter
    private lateinit var view: PromotionsContract.View

    @Before
    fun setUp() {
        presenter = PromotionsPresenter()
        view = mock()
        presenter.attachView(view)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @Test
    fun testLoadPromotionsSuccess() {
        val mock = mock<PromotionsCall>{
            on { mock.getPromotions() } doReturn Observable.just(ArrayList())
        }
        presenter.initPromotionsCall(mock)
        presenter.loadPromotions()
        verify(view).showLoadingIndicator(true)
        verify(view).showPromotions(any())
    }

    @Test
    fun testShowDetails() {
        presenter.showDetail("http://")
        verify(view).showDetail("http://")
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }

}