package com.afcodingtest.koti.utils

import android.widget.TextView
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun TextView.setDisplayContent(content: String?) {
    content?.let {
        text = content
        visibility = View.VISIBLE
    } ?: run {
        visibility = View.GONE
    }
}

fun TextView.setDisplayHtmlContent(content: String?) {
    content?.let {
        val content = content.replace("\\", "")
        val result: Spanned
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
        } else {
            result = Html.fromHtml(content)
        }
        text = result
        movementMethod = LinkMovementMethod.getInstance()
        linksClickable = true
        setLinkTextColor(currentTextColor)
        visibility = View.VISIBLE
    } ?: run {
        visibility = View.GONE
    }
}

fun ImageView.showImage(url: String?) {
    url?.let {
        Picasso.get().load(it).into(this)
        visibility = View.VISIBLE
    } ?: run {
        visibility = View.GONE
    }

}