package com.example.wikipedia.dagger

import com.example.wikipedia.page.articleDetail.ArticleDetailActivity
import com.example.wikipedia.page.articleDetail.DetailModule
import com.example.wikipedia.page.main.MainActivity
import com.example.wikipedia.page.main.MainFragmentProvider
import com.example.wikipedia.page.main.MainModule
import com.example.wikipedia.page.search.SearchActivity
import com.example.wikipedia.page.search.SearchModule
import com.example.wikipedia.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class,MainFragmentProvider::class])
    internal abstract fun provideMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SearchModule::class])
    internal abstract fun provideSearchActivity(): SearchActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [DetailModule::class])
    internal abstract fun provideArticleDetailActivity(): ArticleDetailActivity
}