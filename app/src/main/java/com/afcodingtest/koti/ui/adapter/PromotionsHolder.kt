package com.afcodingtest.koti.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.item_promotion.view.*

class PromotionsHolder(view: View) : RecyclerView.ViewHolder(view) {

    var promotionImage: ImageView = view.bg_image
    var topDescription: TextView = view.topDescription
    var bottomDescription: TextView = view.bottomDescription
    var title: TextView = view.title
    var promoMessage: TextView = view.promoMessage
    var content: LinearLayout = view.content

}