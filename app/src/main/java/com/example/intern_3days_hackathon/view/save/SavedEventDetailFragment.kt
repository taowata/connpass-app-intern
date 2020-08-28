package com.example.intern_3days_hackathon.view.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.intern_3days_hackathon.R


class SavedEventDetailFragment : Fragment() {

    private val safeArgs: SavedEventDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appCompatActivity = activity as AppCompatActivity?
        val actionBar = appCompatActivity?.supportActionBar
        actionBar?.setTitle(R.string.event_list_view_detail)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_saved_event_detail, container, false)
        val webView = v.findViewById<WebView>(R.id.webView)
        webView.webViewClient
        webView.loadUrl(safeArgs.url)
        return v
    }


}
