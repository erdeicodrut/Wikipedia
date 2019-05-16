package com.example.wikipedia.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wikipedia.R
import com.example.wikipedia.holders.ListItemHolder
import com.example.wikipedia.models.Page

class ArticleListItemRecyclerAdapter : RecyclerView.Adapter<ListItemHolder>() {

    val currentResults:ArrayList<Page> = ArrayList()

    override fun getItemCount() = currentResults.size

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val page = currentResults[position]
        holder.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListItemHolder {
        val cardItem = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item,parent,false)
        return ListItemHolder(cardItem)
    }
}