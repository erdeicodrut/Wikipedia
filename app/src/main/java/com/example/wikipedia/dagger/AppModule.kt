package com.example.wikipedia.dagger

import android.app.Application
import android.content.Context
import com.example.wikipedia.Managers.WIkiManager
import com.example.wikipedia.Repositories.ArticleDatabaseOpenHelper
import com.example.wikipedia.Repositories.FavoritesRepository
import com.example.wikipedia.Repositories.HistoryRepository
import com.example.wikipedia.WikiApplication
import com.example.wikipedia.providers.ArticleDataProvider
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    @Provides
    fun provideApplication(application: Application): WikiApplication = application as WikiApplication

    @Provides
    fun provideContext(application: WikiApplication): Context = application

    @Provides
    fun provideDbHelper(context: Context): ArticleDatabaseOpenHelper = ArticleDatabaseOpenHelper(context)

    @Provides
    fun provideManager(
        dataProvider: ArticleDataProvider,
        favoritesRepository: FavoritesRepository,
        historyRepository: HistoryRepository
    ): WIkiManager = WIkiManager(dataProvider, favoritesRepository, historyRepository)

}