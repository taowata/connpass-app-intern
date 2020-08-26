package com.example.intern_3days_hackathon.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.model.response.Event
import java.util.*

class SearchFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appCompatActivity = activity as AppCompatActivity?
        val actionBar = appCompatActivity?.supportActionBar
        actionBar?.setTitle(R.string.search_view)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_search, container, false)
        return setup(v)
    }

    private fun setup(v: View): View {
        EventListRepository.listArticle(PER_PAGE, "android").observe(viewLifecycleOwner, { events: ArrayList<Event> ->
                    showEventListFragment(events)
        })
        return v
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
    }
}
