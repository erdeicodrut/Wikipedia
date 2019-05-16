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

    private val TABLE_NAME: String = "Favorites"

    fun addFavorite(page: Page){
        databaseHelper.use{
            insert(TABLE_NAME,
                "id" to page.pageid,
                "title" to page.title,
                "url" to page.fullurl,
                "thumbnailJson" to Gson().toJson(page.thumbnail))

        }
    }

    fun removeFavoriteById(pageId: Int){
        databaseHelper.use{
            delete(TABLE_NAME, "id = {pageid}", "pageid" to pageId)
        }
    }

    fun isArticleFavorite(pageId : Int) : Boolean{
        var pages = getAllFavorites()
        return pages.any{ page ->
            page.pageid == pageId
        }
    }

    fun getAllFavorites() : ArrayList<Page>{
        var pages =  ArrayList<Page>()

        val articleRowParser = rowParser{id : Int, title: String,url:String,thumbnailJson : String ->
            val page= Page()
            page.title = title
            page.pageid = id
            page.thumbnail = Gson().fromJson(thumbnailJson,Thumbnail::class.java)
            page.fullurl = url

            pages.add(page)
        }

        databaseHelper.use{
            select(TABLE_NAME).parseList(articleRowParser)
        }

        return pages
    }
}