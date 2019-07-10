package com.afcodingtest.koti

import com.afcodingtest.koti.contract.PromotionsContract
import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.networking.APIEndPoint
import com.afcodingtest.koti.presenter.PromotionsPresenter
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class PromotionsPresenterTest {
    @Mock
    private lateinit var presenter: PromotionsPresenter
    @Mock
    private lateinit var view: PromotionsContract.View
    @Mock
    private lateinit var apiEndPoint: APIEndPoint

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = PromotionsPresenter(apiEndPoint)
        presenter.attachView(view)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @Test
    fun testLoadPromotionsSuccess() {
        val list = ArrayList<Promotion>()
        val mockPromotion = Mockito.mock(Promotion::class.java)
        list.add(mockPromotion)
        `when`(apiEndPoint.getPromotions()).thenReturn(Observable.just(list))
        presenter.loadPromotions()
        verify(view).showLoadingIndicator(true)
        verify(view).showPromotions(list)
        verify(view).showLoadingIndicator(false)
    }

    @Test
    fun testShowDetails() {
        presenter.showDetail("http://")
        verify(view).showDetail("http://")
    }


    @Test
    fun testLoadPromotionsFailed() {
        `when`(apiEndPoint.getPromotions()).thenReturn(Observable.error(ArrayIndexOutOfBoundsException("test")))
        presenter.loadPromotions()
        verify(view).showLoadingIndicator(true)
        verify(view).showLoadingError(ArgumentMatchers.anyString())
        verify(view).showLoadingIndicator(false)
    }


    @After
    fun tearDown() {
        presenter.detachView()
        RxAndroidPlugins.reset()
    }

}