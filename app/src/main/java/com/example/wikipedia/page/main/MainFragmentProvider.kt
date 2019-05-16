package com.example.wikipedia.page.main

import com.example.wikipedia.page.explore.ExploreFragment
import com.example.wikipedia.page.favorites.FavoritesFragment
import com.example.wikipedia.page.history.HistoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider {

    @ContributesAndroidInjector
    internal abstract fun provideExploreFragment(): ExploreFragment

    @ContributesAndroidInjector
    internal abstract fun provideFavoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector
    internal abstract fun provideHistoryFragment(): HistoryFragment
}