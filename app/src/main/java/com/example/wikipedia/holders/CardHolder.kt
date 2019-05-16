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

class CardHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    private val articleImageView: ImageView = itemView.findViewById<ImageView>(R.id.article_image)
    private val articleTextView: TextView = itemView.findViewById<TextView>(R.id.article_title)

    private var currentPage : Page? = null

    init{
        itemView.setOnClickListener{
            val detailIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            val pageJson = Gson().toJson(currentPage)
            detailIntent.putExtra("page",pageJson)
            itemView.context?.startActivity(detailIntent)
        }
    }

    fun updateWithPage(page: Page){
        currentPage = page
        articleTextView.text = page.title
        if (page.thumbnail != null){
           Picasso.with(itemView.context).load(page.thumbnail!!.source).into(articleImageView)
        }
    }
}