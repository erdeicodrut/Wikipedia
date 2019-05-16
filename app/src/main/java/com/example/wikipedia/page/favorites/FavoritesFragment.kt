package com.example.wikipedia.page.favorites


import com.example.wikipedia.adapters.ArticleCardRecyclerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.wikipedia.R
import com.example.wikipedia.base.BaseFragment
import com.example.wikipedia.models.Page
import org.jetbrains.anko.doAsync

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesFragment : BaseFragment<FavoritesViewModel, FavoritesViewModelFactory>(FavoritesViewModel::class.java) {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    var favoritesRecycler: androidx.recyclerview.widget.RecyclerView? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecycler = view.findViewById(R.id.favorites_recycler)
        favoritesRecycler!!.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        favoritesRecycler!!.adapter = adapter

        return view
    }


    override fun onResume() {
        super.onResume()

        doAsync {
            adapter.run {
                currentResults.clear()
                val favoriteArticles = wikiManager.getFavorites()
                currentResults.clear()
                currentResults.addAll(favoriteArticles as ArrayList<Page>)
                activity?.runOnUiThread { notifyDataSetChanged() }
            }
        }

    }

}
