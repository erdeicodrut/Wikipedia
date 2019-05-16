package com.example.wikipedia.page.explore


import com.example.wikipedia.adapters.ArticleCardRecyclerAdapter
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.wikipedia.R
import com.example.wikipedia.page.search.SearchActivity
import java.lang.Exception
import com.example.wikipedia.base.BaseFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val ARG_PARAM2 = "param2"


class ExploreFragment : BaseFragment<ExploreViewModel, ExploreViewModelFactory>(ExploreViewModel::class.java){

    companion object {

        private const val CATEGORY = "param1"

         fun newInstance(string : String) = ExploreFragment().apply {
             arguments = Bundle().apply {
                 putString("CATEGORY", string)
             }
         }

    }



    var searchCardView: androidx.cardview.widget.CardView? = null
    var exploreRecycler: androidx.recyclerview.widget.RecyclerView? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()
    var refresher: SwipeRefreshLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_explore, container, false)

        searchCardView = view.findViewById<androidx.cardview.widget.CardView>(R.id.search_card_view)
        exploreRecycler = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.explore_recycler)
        refresher = view.findViewById(R.id.refresher)
        searchCardView!!.setOnClickListener{
            val searchIntent = Intent(context, SearchActivity::class.java)
            context?.startActivity(searchIntent)
        }

        exploreRecycler!!.layoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
            2,
            androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
        )
        exploreRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
            getRandomArticles()
        }


        getRandomArticles()
        return view
    }




    private fun getRandomArticles(){

        refresher?.isRefreshing = true
        try {
            wikiManager.getRandom(15, { wikiResult ->
                adapter.currentResults.clear()
                adapter.currentResults.addAll(wikiResult.query!!.pages)
                activity!!.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    refresher!!.isRefreshing = false
                }

            })
        }catch (ex:Exception){
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(ex.message).setTitle("ooops")
            val dialog = builder.create()
            dialog.show()
        }
    }

}
