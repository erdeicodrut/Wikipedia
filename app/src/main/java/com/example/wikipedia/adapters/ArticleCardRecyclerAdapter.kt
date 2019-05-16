package com.example.wikipedia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wikipedia.R
import com.example.wikipedia.holders.CardHolder
import com.example.wikipedia.models.Page

class ArticleCardRecyclerAdapter : RecyclerView.Adapter<CardHolder>() {

    val currentResults: ArrayList<Page> = ArrayList()

    override fun getItemCount() = currentResults.size


    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val page = currentResults[position]
        holder.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val cardItem = LayoutInflater.from(parent.context).inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }
}