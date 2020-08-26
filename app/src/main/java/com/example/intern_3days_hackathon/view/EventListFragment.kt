package com.example.intern_3days_hackathon.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.model.response.Event
import kotlinx.android.synthetic.main.fragment_event_list.*
import java.util.*


class EventListFragment : Fragment() {

    private var events: ArrayList<Event>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { argument ->
            events = argument.getSerializable(CONNPASS_EVENTS) as? ArrayList<Event>
        }

        val appCompatActivity = activity as AppCompatActivity?
        val actionBar = appCompatActivity?.supportActionBar
        actionBar?.setTitle(R.string.event_list_view)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        view?.let { view ->
            val recyclerView: RecyclerView = rv_menu
            val layoutManager = LinearLayoutManager(view.context)
            recyclerView.layoutManager = layoutManager
            val adapter = EventListViewAdapter(events)
            recyclerView.adapter = adapter
            val decorator = DividerItemDecoration(context, layoutManager.orientation)
            recyclerView.addItemDecoration(decorator)

            adapter.setOnItemClickListener(object : EventListViewAdapter.OnItemClickListener {
                override fun onItemClickListener(item: Event) {
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(view.context, Uri.parse(item.eventURL))
                }
            })
        }
    }

    companion object {
        private const val CONNPASS_EVENTS = "connpass_events"
        fun newInstance(response: ArrayList<Event>): EventListFragment {
            val fragment = EventListFragment()
            val args = Bundle()
            args.putSerializable(CONNPASS_EVENTS, response)
            fragment.arguments = args
            return fragment
        }
    }
}
