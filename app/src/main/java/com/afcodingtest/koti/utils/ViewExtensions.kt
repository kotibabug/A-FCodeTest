package com.afcodingtest.koti.utils

import android.widget.TextView
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod


fun TextView.setTextWithHtml(html: String) {
    val content = html.replace("\\","")
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

}