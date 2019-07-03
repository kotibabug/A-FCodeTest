package com.afcodingtest.koti.networking


abstract class Callback<T> {
    abstract fun onResponseSuccess(response : T)
    abstract fun onResponseError(error: String)
}