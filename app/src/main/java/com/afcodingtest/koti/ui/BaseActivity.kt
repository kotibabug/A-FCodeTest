package com.afcodingtest.koti.ui

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.afcodingtest.koti.BaseView
import kotlinx.android.synthetic.main.activity_promotions.*

open class BaseActivity : AppCompatActivity(), BaseView {

    override fun hideLoadingIndicator() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showLoadingIndicator() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showLoadingError(error: String) {
        Snackbar.make(container, error, Snackbar.LENGTH_LONG).show()
    }
}