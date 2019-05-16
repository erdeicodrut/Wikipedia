package com.example.wikipedia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wikipedia.R
import com.example.wikipedia.holders.CardHolder
import com.example.wikipedia.models.Page

class ArticleCardRecyclerAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<CardHolder>() {

    val currentResults: ArrayList<Page> = ArrayList<Page>()

    override fun getItemCount(): Int {
        return currentResults.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        var page=currentResults[position]
        holder.updateWithPage(page)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CardHolder {
        var cardItem = LayoutInflater.from(parent.context).inflate(R.layout.article_card_item,parent,false)
        return CardHolder(cardItem)
    }
}