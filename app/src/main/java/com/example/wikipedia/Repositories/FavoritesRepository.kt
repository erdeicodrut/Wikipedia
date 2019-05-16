package com.example.wikipedia.Repositories

import com.google.gson.Gson
import com.example.wikipedia.models.Page
import com.example.wikipedia.models.Thumbnail
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import javax.inject.Inject

class FavoritesRepository @Inject constructor(val databaseHelper: ArticleDatabaseOpenHelper) {

    private val TABLENAME = "Favorites"

    fun addFavorite(page: Page) {
        databaseHelper.use {
            insert(
                TABLENAME,
                "id" to page.pageid,
                "title" to page.title,
                "url" to page.fullurl,
                "thumbnailJson" to Gson().toJson(page.thumbnail)
            )

        }
    }

    fun removeFavoriteById(pageId: Int) {
        databaseHelper.use {
            delete(TABLENAME, "id = {pageid}", "pageid" to pageId)
        }
    }

    fun isArticleFavorite(pageId: Int): Boolean {
        val pages = getAllFavorites()
        return pages.any { page ->
            page.pageid == pageId
        }
    }

    fun getAllFavorites(): ArrayList<Page> {
        val pages = ArrayList<Page>()

        val articleRowParser = rowParser { id: Int, title: String, url: String, thumbnailJson: String ->
            val page = Page()
            page.title = title
            page.pageid = id
            page.thumbnail = Gson().fromJson(thumbnailJson, Thumbnail::class.java)
            page.fullurl = url

            pages.add(page)
        }

        databaseHelper.use {
            select(TABLENAME).parseList(articleRowParser)
        }

        return pages
    }
}