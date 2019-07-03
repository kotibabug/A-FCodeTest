package com.afcodingtest.koti.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.afcodingtest.koti.R
import com.afcodingtest.koti.model.Promotion
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
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return promotionsList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        promotionsList[position].backgroundImage?.let {
            Picasso.get().load(it).into(holder.promotionImage)
        }

    }

    inner class ItemViewHolder : RecyclerView.ViewHolder {
        var promotionImage: ImageView

        constructor(view: View) : super(view) {
            promotionImage = view.bg_image
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