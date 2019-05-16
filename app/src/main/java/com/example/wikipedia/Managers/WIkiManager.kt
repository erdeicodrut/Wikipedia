package com.example.wikipedia.Managers

import com.example.wikipedia.Repositories.FavoritesRepository
import com.example.wikipedia.Repositories.HistoryRepository
import com.example.wikipedia.models.Page
import com.example.wikipedia.models.WikiResult
import com.example.wikipedia.providers.ArticleDataProvider
import javax.inject.Inject

class WIkiManager @Inject constructor(private val provider: ArticleDataProvider,
                                      private val favoritesRepository: FavoritesRepository,
                                      private val historyRepository: HistoryRepository
) {

    private var favoritesCache: ArrayList<Page>? = null
    private var historyCache: ArrayList<Page>? = null

    fun search(term:String,skip:Int,take:Int,handler:(result: WikiResult) -> Unit?){
        return provider.search(term,skip,take,handler)
    }

    fun getRandom(take:Int,handler:(result: WikiResult) -> Unit?) {
        return provider.getRandom(take, handler)
    }

    fun getHistory() : ArrayList<Page>? {
        if (historyCache == null)
            historyCache = historyRepository.getAllHistory()
        return historyCache
    }

    fun getFavorites() : ArrayList<Page>? {
        if (favoritesCache == null)
            favoritesCache = favoritesRepository.getAllFavorites()
        return favoritesCache
    }

    fun addFavorite(page: Page){
        favoritesCache?.add(page)
        favoritesRepository.addFavorite(page)
    }

    fun removeFavorite(pageId: Int){
        favoritesRepository.removeFavoriteById(pageId)
        favoritesCache = favoritesCache!!.filter{ it.pageid != pageId
        } as ArrayList<Page>

    }

    fun getIsFavorite(pageId: Int) : Boolean{
        return favoritesRepository.isArticleFavorite(pageId)
    }

    fun addHistory(page: Page){
        if (historyRepository.addHistory(page))
            historyCache?.add(page)
    }

    fun clearHistory(){
        historyCache?.clear()
        val allHistory = historyRepository.getAllHistory()
        allHistory.forEach{historyRepository.removeHistoryById(it.pageid!!)}
    }
}