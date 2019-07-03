package com.afcodingtest.koti.networking

import com.afcodingtest.koti.model.Promotion
import com.google.gson.Gson
import com.sample.networking.APIService
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

object PromotionsCall {

    @Throws(IOException::class)
    private fun makeHttpCall(url: String): String? {
        val connection: HttpURLConnection
        var inputStream: InputStream
        var response: String?

        val url = URL(url)
        connection = url.openConnection() as HttpURLConnection
        connection.connect()
        inputStream = connection.inputStream
        response = inputStream.reader().readText()
        connection.disconnect()
        inputStream.close()
        return response
    }

    fun getPromotions(): Observable<List<Promotion>> {
        return Observable.create { emitter ->
            try {
                val response = makeHttpCall(APIService.PROMOTIONS_URL)
                val promotionList = Gson().fromJson(
                    response,
                    Array<Promotion>::class.java
                ).toList()
                emitter.onNext(promotionList)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

}