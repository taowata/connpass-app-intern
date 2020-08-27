package com.example.intern_3days_hackathon.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.model.response.Event
import kotlinx.android.synthetic.main.fragment_event_list_detail.*
import java.util.*


class EventListDetailFragment : Fragment() {

    private var events: ArrayList<Event>? = ArrayList()
    private var event: Event? = null

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
        return inflater.inflate(R.layout.fragment_event_list_detail, container, false)
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