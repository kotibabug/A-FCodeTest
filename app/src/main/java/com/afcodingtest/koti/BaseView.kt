package com.afcodingtest.koti

interface BaseView {
    fun showLoadingIndicator(isActive: Boolean)
    fun showLoadingError(error: String)
}