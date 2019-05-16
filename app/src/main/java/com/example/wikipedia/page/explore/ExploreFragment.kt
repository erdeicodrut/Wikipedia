package com.example.wikipedia.page.explore


import com.example.wikipedia.adapters.ArticleCardRecyclerAdapter
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.example.wikipedia.R
import com.example.wikipedia.page.search.SearchActivity
import java.lang.Exception
import com.example.wikipedia.base.BaseFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class ExploreFragment : BaseFragment<ExploreViewModel, ExploreViewModelFactory>(ExploreViewModel::class.java) {

    companion object {

        fun newInstance(string: String) = ExploreFragment().apply {
            arguments = Bundle().apply {
                putString("CATEGORY", string)
            }
        }

    }

    lateinit var searchCardView: CardView
    lateinit var exploreRecycler: RecyclerView
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()
    lateinit var refresher: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        searchCardView = view.findViewById(R.id.search_card_view)
        exploreRecycler = view.findViewById(R.id.explore_recycler)
        refresher = view.findViewById(R.id.refresher)
        searchCardView.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }

        exploreRecycler.layoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
            2,
            androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
        )
        exploreRecycler.adapter = adapter

        refresher.setOnRefreshListener {
            getRandomArticles()
        }

        getRandomArticles()
        return view
    }


    private fun getRandomArticles() {

        refresher.isRefreshing = true
        try {
            wikiManager.getRandom(15) { wikiResult ->
                adapter.run {
                    currentResults.clear()
                    currentResults += wikiResult.query!!.pages
                    activity?.runOnUiThread {
                        notifyDataSetChanged()
                        refresher.isRefreshing = false
                    }
                }

            }
        } catch (ex: Exception) {
            val builder = AlertDialog.Builder(activity)
            builder.apply {
                setMessage(ex.message).setTitle("ooops")
                create()
                show()
            }
        }
    }

}
