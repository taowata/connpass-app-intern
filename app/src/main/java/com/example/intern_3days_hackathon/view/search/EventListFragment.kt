package com.example.intern_3days_hackathon.view.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.model.Event
import kotlinx.android.synthetic.main.fragment_event_list.*
import java.util.*


class EventListFragment : Fragment() {

    private var events: ArrayList<Event>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { argument ->
            events = argument.getSerializable(CONNPASS_EVENTS) as? ArrayList<Event>
        }

        val activity = requireActivity()
        val actionBar = activity.actionBar
        actionBar?.setTitle(R.string.event_list_view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i("onActivityCreateds", "${events!![0].title}")
        super.onActivityCreated(savedInstanceState)
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
                    showEventListDetailFragment(item)
                }
            })
        }
    }

    private fun showEventListDetailFragment(item: Event) {
        fragmentManager?.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.nav_fragment, EventListDetailFragment.newInstance(item))
                    .addToBackStack(null)
                    .commit()
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
