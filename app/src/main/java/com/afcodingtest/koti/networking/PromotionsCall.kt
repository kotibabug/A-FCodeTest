package com.afcodingtest.koti.networking

import android.util.Log
import com.afcodingtest.koti.model.Promotion
import com.google.gson.Gson
import io.reactivex.Observable
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class PromotionsCall {

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

    fun getPromotions(): Observable<ArrayList<Promotion>> {
        return Observable.create { emitter ->
            try {
                val response = makeHttpCall(PROMOTIONS_URL)
                val promotionList = Gson().fromJson(
                    response,
                    Array<Promotion>::class.java
                ).toList()
                emitter.onNext(ArrayList(promotionList))
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }

    companion object {
        const val PROMOTIONS_URL = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json"
    }
}