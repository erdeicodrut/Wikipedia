package com.example.wikipedia.page.history


import com.example.wikipedia.Managers.WIkiManager
import com.example.wikipedia.adapters.ArticleListItemRecyclerAdapter
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.wikipedia.WikiApplication
import android.view.*

import com.example.wikipedia.R
import com.example.wikipedia.base.BaseFragment
import com.example.wikipedia.models.Page
import com.example.wikipedia.page.favorites.FavoritesViewModel
import com.example.wikipedia.page.favorites.FavoritesViewModelFactory
import com.example.wikipedia.page.favorites.HistoryViewModel
import com.example.wikipedia.page.favorites.HistoryViewModelFactory
import dagger.android.support.DaggerFragment
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HistoryFragment : BaseFragment<HistoryViewModel, HistoryViewModelFactory>(HistoryViewModel::class.java){

    companion object {

        fun newInstance() = HistoryFragment()

    }


    var historyRecycler: androidx.recyclerview.widget.RecyclerView? = null
    var adapter: ArticleListItemRecyclerAdapter = ArticleListItemRecyclerAdapter()

    init{
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_history, container, false)

        historyRecycler = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.history_recycler)
        historyRecycler!!.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        historyRecycler!!.adapter = adapter
        return view
    }

    override fun onResume() {
        super.onResume()

        doAsync {


            adapter.currentResults.clear()
            val historyArticles = wikiManager!!.getHistory()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(historyArticles as ArrayList<Page>)

            activity!!.runOnUiThread { adapter.notifyDataSetChanged() }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.history_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.clear_history){
            activity!!.alert("Are you sure?","Confirm"){
                yesButton {
                    adapter.currentResults.clear()
                    doAsync {
                        wikiManager!!.clearHistory()
                    }
                    activity!!.runOnUiThread { adapter.notifyDataSetChanged() }
                }
                noButton {  }
            }.show()

        }
        return true
    }


}
