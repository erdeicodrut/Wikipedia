package com.example.wikipedia.page.articleDetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.wikipedia.Managers.WIkiManager
import com.example.wikipedia.R
import com.example.wikipedia.WikiApplication
import com.example.wikipedia.base.BaseActivity
import com.example.wikipedia.models.Page
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.jetbrains.anko.toast

class ArticleDetailActivity : BaseActivity<DetailViewModel, DetailViewModelFactory>(DetailViewModel::class.java) {

    private lateinit var currentPage: Page
    private lateinit var wikiManager: WIkiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        wikiManager = (applicationContext as WikiApplication).wikiManager

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<Page>(wikiPageJson, Page::class.java)

        supportActionBar?.title = currentPage.title

        article_detail_webview?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?) = true
        }


        article_detail_webview.loadUrl(currentPage.fullurl)
        article_detail_webview.webViewClient = WebViewClient()

        wikiManager.addHistory(currentPage)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {

            if (item.itemId == android.R.id.home) {
                finish()
            } else if (item.itemId == R.id.action_favorite) {

                try {
                    currentPage.pageid?.let {

                        if (wikiManager.getIsFavorite(it)) {
                            wikiManager.removeFavorite(it)
                            toast("Article removed from favorites")
                        } else {
                            wikiManager.addFavorite(currentPage)
                            toast("Article added to favorites")
                        }

                    }
                } catch (ex: Exception) {
                    toast("Unable to update")
                }
            }
        }
        return true
    }
}
