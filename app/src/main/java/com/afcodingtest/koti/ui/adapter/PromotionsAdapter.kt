package com.afcodingtest.koti.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.afcodingtest.koti.R
import com.afcodingtest.koti.model.Content
import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.utils.setDisplayContent
import com.afcodingtest.koti.utils.setDisplayHtmlContent
import com.afcodingtest.koti.utils.showImage
import kotlinx.android.synthetic.main.item_promotion.view.*

class PromotionsAdapter(val onItemClick:(String) -> Unit) :
    RecyclerView.Adapter<PromotionsHolder>() {

    private var promotionsList: ArrayList<Promotion>? = null

    fun setData(promotionsList: List<Promotion>) {
        this.promotionsList = ArrayList(promotionsList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PromotionsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)
        return PromotionsHolder(view)
    }

    override fun getItemCount(): Int {
        return promotionsList?.size ?: 0
    }

    override fun onBindViewHolder(holder: PromotionsHolder, position: Int) {
        val promotion = promotionsList?.get(position)
        holder.promotionImage.showImage(promotion?.backgroundImage ?: "")
        holder.topDescription.setDisplayContent(promotion?.topDescription ?: "")
        holder.title.setDisplayContent(promotion?.title ?: "")
        holder.promoMessage.setDisplayContent(promotion?.promoMessage ?: "")
        holder.bottomDescription.setDisplayHtmlContent(promotion?.bottomDescription ?: "")

        holder.content.removeAllViews()
        promotion?.content?.forEach {
            addContentButton(holder.content, it)
        }

    }

    private fun addContentButton(layout: LinearLayout, content: Content) {
        val contentBtn: TextView =
            LayoutInflater.from(layout.context).inflate(
                R.layout.item_textbutton,
                layout,
                false
            ) as TextView
        layout.addView(contentBtn)
        contentBtn.text = content.title
        contentBtn.tag = content.target
        contentBtn.setOnClickListener {
            onItemClick(it.tag.toString())
        }
        val params = contentBtn.layoutParams as LinearLayout.LayoutParams
        params.setMargins(0, 0, 0, 20)
        contentBtn.layoutParams = params
    }

    fun clear() {
        promotionsList?.clear()
        notifyDataSetChanged()
    }

}