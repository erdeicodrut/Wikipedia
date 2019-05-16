package com.example.wikipedia.page.articleDetail

import dagger.Module
import dagger.Provides


@Module
class DetailModule {

    @Provides
    internal fun provideDetailViewModelFactory() = DetailViewModelFactory()

}