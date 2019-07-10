package com.afcodingtest.koti

import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.networking.APIEndPoint
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ApiEndPointTest {
    lateinit var apiEndPoint: APIEndPoint

    @Before
    fun setUp() {
        apiEndPoint = mock(APIEndPoint::class.java)
    }

    @Test
    fun testGetPromotions() {
        val observer: TestObserver<List<Promotion>> = TestObserver.create()
        val list = ArrayList<Promotion>()
        val mockPromotion = mock(Promotion::class.java)
        list.add(mockPromotion)
        `when`(apiEndPoint.getPromotions()).thenReturn(Observable.just(list))
        `when`(mockPromotion.title).thenReturn("test")
        apiEndPoint.getPromotions().subscribe(observer)
        observer.assertNoErrors()
        observer.assertComplete()
        assertEquals("test", observer.values().component1()[0].title)

    }

}