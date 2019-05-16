package com.example.wikipedia.page.search

import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    internal fun provideSearchViewModelFactory() = SearchViewModelFactory()

}