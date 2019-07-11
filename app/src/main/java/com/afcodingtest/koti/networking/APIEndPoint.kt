package com.afcodingtest.koti.networking

import com.afcodingtest.koti.model.Promotion
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface APIEndPoint {
    @GET("anf/nativeapp/qa/codetest/codeTest_exploreData.json")
    fun getPromotions(): Single<List<Promotion>>
}