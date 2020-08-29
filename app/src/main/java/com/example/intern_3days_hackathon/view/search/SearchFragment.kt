package com.example.intern_3days_hackathon.view.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.model.Event
import com.example.intern_3days_hackathon.view.user.UserInformationViewModel
import java.util.*

class SearchFragment : Fragment() {

    var searchKey: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_search, container, false)

        val activity = requireActivity()
        val searchView = activity.findViewById<SearchView>(R.id.searchView)
        val linearLayout = activity.findViewById<LinearLayout>(R.id.linearLayout)
        searchView.visibility = View.VISIBLE
        linearLayout.visibility = View.VISIBLE

        searchKey =  arguments?.getString(SEARCH_KEY)
        Log.i("onCreateView", "${searchKey}")
        return setup(v)
    }

    private fun setup(v: View): View {
        if (searchKey != null) {
            val keyword = searchKey
            CallEventListRepository(keyword)
            return v
        } else {
            // お気に入りワードを設定
            val prefs = context?.getSharedPreferences(UserInformationViewModel.PREF, Context.MODE_PRIVATE)
            val favWord1 = prefs?.getString(UserInformationViewModel.KEY_FAV_WORD1, "Kotlin")
            CallEventListRepository(favWord1)
            return v
        }
    }

    private fun CallEventListRepository(keyword: String?) {
        EventListRepository.listArticle(PER_PAGE, keyword).observe(viewLifecycleOwner, androidx.lifecycle.Observer { events: ArrayList<Event> ->
            showEventListFragment(events)
        })
    }

    private fun showEventListFragment(events: ArrayList<Event>) {
        fragmentManager?.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.nav_fragment, EventListFragment.newInstance(events))
                    .addToBackStack(null)
                    .commit()
        }
    }

    companion object {
        private const val PER_PAGE = 10
        private const val SEARCH_KEY = "searchKey"
        fun newInstance(searchKey: String): SearchFragment {
            val fragment = SearchFragment()
            val args = Bundle()
            args.putString(SEARCH_KEY, searchKey)
            fragment.arguments = args
            return fragment
        }
    }
}
