package com.example.wikipedia.page.main

import com.example.wikipedia.page.explore.ExploreViewModelFactory
import com.example.wikipedia.page.favorites.FavoritesViewModelFactory
import com.example.wikipedia.page.history.HistoryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    internal fun provideMainViewModelFactory() = MainViewModelFactory()

    @Provides
    internal fun provideExploreViewModelFactory() = ExploreViewModelFactory()

    @Provides
    internal fun provideFavoritesViewModelFactory() = FavoritesViewModelFactory()

    @Provides
    internal fun provideHistoryViewModelFactory() = HistoryViewModelFactory()
}