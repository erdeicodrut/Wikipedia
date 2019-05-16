package com.example.wikipedia.page.history


import com.example.wikipedia.adapters.ArticleListItemRecyclerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import androidx.recyclerview.widget.RecyclerView

import com.example.wikipedia.R
import com.example.wikipedia.base.BaseFragment
import com.example.wikipedia.models.Page
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.yesButton

/**
 * A simple [Fragment] subclass.
 *
 */
class HistoryFragment : BaseFragment<HistoryViewModel, HistoryViewModelFactory>(HistoryViewModel::class.java) {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    lateinit var historyRecycler: RecyclerView
    var adapter: ArticleListItemRecyclerAdapter = ArticleListItemRecyclerAdapter()

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        historyRecycler = view.findViewById(R.id.history_recycler)
        historyRecycler.run {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = adapter
        }
        return view
    }

    override fun onResume() {
        super.onResume()

        doAsync {


            adapter.currentResults.clear()
            val historyArticles = wikiManager.getHistory()
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
        if (item?.itemId == R.id.clear_history) {
            activity?.alert("Are you sure?", "Confirm") {
                yesButton {
                    adapter.currentResults.clear()
                    doAsync {
                        wikiManager.clearHistory()
                    }
                    activity?.runOnUiThread { adapter.notifyDataSetChanged() }
                }
            }?.show()

        }
        return true
    }


}
