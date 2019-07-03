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
import com.afcodingtest.koti.utils.setTextWithHtml
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_promotion.view.*

class PromotionsAdapter :
    RecyclerView.Adapter<PromotionsAdapter.ItemViewHolder> {

    private var promotionsList: ArrayList<Promotion>

    constructor(promotionsList: ArrayList<Promotion>) : super() {
        this.promotionsList = promotionsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)
        val promotion = promotionsList[position]
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return promotionsList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val promotion = promotionsList[position]
        promotion.backgroundImage?.let {
            holder.promotionImage.visibility = View.VISIBLE
            Picasso.get().load(it).into(holder.promotionImage)
        } ?: run {
            holder.promotionImage.visibility = View.GONE
        }
        promotion.topDescription?.let {
            holder.topDescription.visibility = View.VISIBLE
            holder.topDescription.text = it
        } ?: run {
            holder.topDescription.visibility = View.GONE
        }

        promotion.title?.let {
            holder.title.visibility = View.VISIBLE
            holder.title.text = it
        } ?: run {
            holder.title.visibility = View.GONE
        }

        promotion.promoMessage?.let {
            holder.promoMessage.visibility = View.VISIBLE
            holder.promoMessage.text = it
        } ?: run {
            holder.promoMessage.visibility = View.GONE
        }

        promotion.bottomDescription?.let {
            holder.bottomDescription.visibility = View.VISIBLE
            holder.bottomDescription.setTextWithHtml(it)
        } ?: run {
            holder.bottomDescription.visibility = View.GONE
        }

        holder.content.removeAllViews()
        promotion.content?.forEach {
            val contentBtn: TextView =
                LayoutInflater.from(holder.content.context).inflate(
                    R.layout.item_textbutton,
                    holder.content,
                    false
                ) as TextView
            holder.content.addView(contentBtn)
            contentBtn.text = it.title
            val params = contentBtn.layoutParams as LinearLayout.LayoutParams
            params.setMargins(0, 0, 0, 20)
            contentBtn.layoutParams = params
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

        /* override fun onClick(view: View) {
             mClickListener?.onItemClick(view, adapterPosition)
         }*/
    }

    /*fun setOnClickListener(listener: ItemClickListener) {
        mClickListener = listener
    }
    */

    fun clear() {
        promotionsList.clear()
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}