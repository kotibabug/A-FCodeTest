package com.afcodingtest.koti.ui.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.afcodingtest.koti.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_webview)
        webview.loadUrl(intent.getStringExtra(getString(R.string.extra_target)))
    }

}