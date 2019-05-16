package com.example.wikipedia

import com.example.wikipedia.Managers.WIkiManager
import com.example.wikipedia.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class WikiApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build().apply{
            inject(this@WikiApplication)
        }

    @Inject
    lateinit var wikiManager: WIkiManager
    //var favoritesRepository: FavoritesRepository? = null
    //var historyRepository: HistoryRepository? = null
    //var wikiProvider: ArticleDataProvider? = null
    //var wikiManager: WIkiManager? = null


    override fun onCreate() {
        super.onCreate()


        //dbHelper = ArticleDatabaseOpenHelper(applicationContext)
        //favoritesRepository = FavoritesRepository(dbHelper!!)
        //historyRepository = HistoryRepository(dbHelper!!)
        //wikiProvider = ArticleDataProvider()
        //wikiManager = WIkiManager(wikiProvider!!,favoritesRepository!!,historyRepository!!)
    }
}