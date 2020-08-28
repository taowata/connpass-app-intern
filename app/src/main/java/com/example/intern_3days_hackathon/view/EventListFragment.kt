package com.example.intern_3days_hackathon.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

//        val button = view?.findViewById<Button>(R.id.button_category_num1)
//        button_category_num1.setOnClickListener {
//            Log.i("クエリ", "Tap")
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val button = view.findViewById<Button>(R.id.button)
//        button.setOnClickListener {
//            view?.let {
//                val searchKey  = it.editText.text.toString()
//                EventListRepository.listArticle(PAGE, searchKey).observe(viewLifecycleOwner, Observer { qiitaListResponse: ArrayList<Event> ->
//                    showEventListFragment(qiitaListResponse)
//                })
//            }
//        }

//        val searchView = view.findViewById<SearchView>(R.id.searchView)
//        searchView.let {
//            searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    val searchkey = newText.toString()
//                    Log.i("クエリ", "${searchkey}")
//                    view?.let {
//                        EventListRepository.listArticle(PAGE, searchkey).observe(viewLifecycleOwner, Observer { connpassResponse: ArrayList<Event> ->
//                            showEventListFragment(connpassResponse)
//                        })
//                    }
//                    return true
//                }
//            })
//        }
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
                    showEventListDetailFragment(item)
                }
            })
        }
    }

    private fun showEventListFragment(events: ArrayList<Event>) {
        fragmentManager?.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.nav_fragment, EventListFragment.newInstance(events))
                    .addToBackStack(null)
                    .commit()
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
