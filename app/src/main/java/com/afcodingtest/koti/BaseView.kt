package com.afcodingtest.koti

interface BaseView {
    fun showLoadingIndicator()
    fun hideLoadingIndicator()
    fun showLoadingError(error: String)
}