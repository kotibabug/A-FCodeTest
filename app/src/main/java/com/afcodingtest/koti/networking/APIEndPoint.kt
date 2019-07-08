package com.afcodingtest.koti.networking

import com.afcodingtest.koti.model.Promotion
import io.reactivex.Observable
import retrofit2.http.GET

interface APIEndPoint {
    @GET("anf/nativeapp/qa/codetest/codeTest_exploreData.json")
    fun getPromotions(): Observable<List<Promotion>>
}