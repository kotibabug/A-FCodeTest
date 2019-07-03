package com.afcodingtest.koti

interface BaseView<T> {
    fun setPresenter(presenter: T)
    fun showLoadingIndicator(isActive: Boolean)
    fun showLoadingError(error: String)
}