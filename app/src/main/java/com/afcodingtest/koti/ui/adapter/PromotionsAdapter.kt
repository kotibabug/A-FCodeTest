package com.afcodingtest.koti.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.afcodingtest.koti.R
import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.utils.setDisplayContent
import com.afcodingtest.koti.utils.setDisplayHtmlContent
import com.afcodingtest.koti.utils.showImage
import kotlinx.android.synthetic.main.item_promotion.view.*

class PromotionsAdapter :
    RecyclerView.Adapter<PromotionsAdapter.ItemViewHolder> {

    private var promotionsList: ArrayList<Promotion>
    private lateinit var mClickListener: ContentClickListener

    constructor(promotionsList: ArrayList<Promotion>) : super() {
        this.promotionsList = promotionsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return promotionsList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val promotion = promotionsList[position]
        holder.promotionImage.showImage(promotion.backgroundImage)
        holder.topDescription.setDisplayContent(promotion.topDescription)
        holder.title.setDisplayContent(promotion.title)
        holder.promoMessage.setDisplayContent(promotion.promoMessage)
        holder.bottomDescription.setDisplayHtmlContent(promotion.bottomDescription)

        holder.content.removeAllViews()
        promotion.content?.forEach {
            addContentButton(holder.content, it)
        }

    }

    inner class ItemViewHolder : RecyclerView.ViewHolder {
        var promotionImage: ImageView
        var topDescription: TextView
        var bottomDescription: TextView
        var title: TextView
        var promoMessage: TextView
        var content: LinearLayout

        constructor(view: View) : super(view) {
            promotionImage = view.bg_image
            topDescription = view.topDescription
            bottomDescription = view.bottomDescription
            title = view.title
            promoMessage = view.promoMessage
            content = view.content
        }

    }

    private fun addContentButton(layout: LinearLayout, content: Promotion.Content) {
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
            mClickListener.onContentClick(it.tag.toString())
        }
        val params = contentBtn.layoutParams as LinearLayout.LayoutParams
        params.setMargins(0, 0, 0, 20)
        contentBtn.layoutParams = params
    }

    fun setOnContentClickListener(listener: ContentClickListener) {
        mClickListener = listener
    }


    fun clear() {
        promotionsList.clear()
        notifyDataSetChanged()
    }

    interface ContentClickListener {
        fun onContentClick(target: String?)
    }
}