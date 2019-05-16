package com.example.wikipedia.page.articleDetail

import com.example.wikipedia.Managers.WIkiManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_article_detail.*
import com.example.wikipedia.models.Page
import android.view.Menu
import com.example.wikipedia.R
import com.example.wikipedia.WikiApplication
import com.example.wikipedia.base.BaseActivity
import com.example.wikipedia.page.main.MainViewModel
import com.example.wikipedia.page.main.MainViewModelFactory
import org.jetbrains.anko.toast
import java.lang.Exception

class ArticleDetailActivity : BaseActivity<DetailViewModel, DetailViewModelFactory>(DetailViewModel::class.java){

    private var currentPage: Page? = null
    private var wikiManager: WIkiManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        wikiManager = (applicationContext as WikiApplication).wikiManager

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)



        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<Page>(wikiPageJson,Page::class.java)

        supportActionBar?.title = currentPage?.title

        article_detail_webview?.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return true
            }
        }


        article_detail_webview.loadUrl(currentPage!!.fullurl)
        article_detail_webview.webViewClient = WebViewClient()

        wikiManager!!.addHistory(currentPage!!)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
       if(item!!.itemId == android.R.id.home){
           finish()
       }
        else if(item.itemId == R.id.action_favorite){
           try {
               if (wikiManager!!.getIsFavorite(currentPage!!.pageid!!)) {
                   wikiManager!!.removeFavorite(currentPage!!.pageid!!)
                   toast("Article removed from favorites")
               } else {
                   wikiManager!!.addFavorite(currentPage!!)
                   toast("Article favorited")
               }
           } catch (ex: Exception){
               toast("unable to update")
           }
       }

        return true
    }
}