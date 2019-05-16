package com.example.wikipedia.page.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.wikipedia.R
import com.example.wikipedia.base.BaseActivity
import com.example.wikipedia.page.explore.ExploreFragment
import com.example.wikipedia.page.favorites.FavoritesFragment
import com.example.wikipedia.page.history.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, MainViewModelFactory>(MainViewModel::class.java){

    private var exploreFragment : ExploreFragment = ExploreFragment.newInstance("F")
    private val favoritesFragment : FavoritesFragment = FavoritesFragment.newInstance()
    private val historyFragment : HistoryFragment = HistoryFragment.newInstance()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
        when (item.itemId){
            R.id.navigation_explore -> transaction.replace(R.id.fragment_container,exploreFragment)
            R.id.navigation_favorites -> transaction.replace(R.id.fragment_container,favoritesFragment)
            R.id.navigation_history -> transaction.replace(R.id.fragment_container,historyFragment)
        }

        transaction.commit()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container,exploreFragment)
        transaction.commit()
    }
}
