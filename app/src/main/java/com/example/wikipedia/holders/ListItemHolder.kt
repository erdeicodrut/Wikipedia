package com.example.wikipedia.holders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.wikipedia.page.articleDetail.ArticleDetailActivity
import com.example.wikipedia.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.example.wikipedia.models.Page

class ListItemHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    private val articleImageView: ImageView = itemView.findViewById<ImageView>(R.id.result_icon)
    private val articleTextView: TextView = itemView.findViewById<TextView>(R.id.result_title)

    private var currentPage : Page? = null

    fun updateWithPage(page: Page){
        currentPage = page
        articleTextView.text = page.title
        if (page.thumbnail != null){
            Picasso.with(itemView.context).load(page.thumbnail!!.source).into(articleImageView)
        }
    }

    init{
        itemView.setOnClickListener{
            val detailIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            val pageJson = Gson().toJson(currentPage)
            detailIntent.putExtra("page",pageJson)
            itemView.context?.startActivity(detailIntent)
        }
    }
}