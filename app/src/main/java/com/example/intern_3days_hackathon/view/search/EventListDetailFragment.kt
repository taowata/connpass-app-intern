package com.example.intern_3days_hackathon.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.data.EventDatabase
import com.example.intern_3days_hackathon.data.SavedEvent
import com.example.intern_3days_hackathon.model.Event
import com.example.intern_3days_hackathon.view.save.SavedEventViewModel
import com.example.intern_3days_hackathon.view.save.SavedEventViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_event_list_detail.*


class EventListDetailFragment : Fragment() {

    private var event: Event? = null

    private lateinit var viewModel: SavedEventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { argument ->
            event = argument.getSerializable(CONNPASS_EVENTS) as? Event
        }

        val appCompatActivity = activity as AppCompatActivity?
        val actionBar = appCompatActivity?.supportActionBar
        actionBar?.setTitle(R.string.event_list_view_detail)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val activity = requireActivity()
        val searchView = activity.findViewById<SearchView>(R.id.searchView)
        val linearLayout = activity.findViewById<LinearLayout>(R.id.linearLayout)
        searchView.visibility = View.GONE
        linearLayout.visibility = View.GONE

        return inflater.inflate(R.layout.fragment_event_list_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewModelの初期化
        val application = requireNotNull(this.activity).application
        val dataSource = EventDatabase.getInstance(application).savedEventDao
        val viewModelFactory = SavedEventViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SavedEventViewModel::class.java)

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
            val eventToSave = SavedEvent(
                    title = event!!.title,
                    date = event!!.startedAt,
                    location = event!!.address ?: "不明",
                    url = event!!.eventURL,
                    )
            viewModel.insertEvent(eventToSave)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupWebView(event!!)
    }

    private fun setupWebView(event: Event) {
        view?.let {
            val webView: WebView = webView
            webView.loadUrl(event!!.eventURL)
        }
    }

    companion object {
        private const val CONNPASS_EVENTS = "connpass_events"
        fun newInstance(response: Event): EventListDetailFragment {
            val fragment = EventListDetailFragment()
            val args = Bundle()
            args.putSerializable(CONNPASS_EVENTS, response)
            fragment.arguments = args
            return fragment
        }
    }
}
