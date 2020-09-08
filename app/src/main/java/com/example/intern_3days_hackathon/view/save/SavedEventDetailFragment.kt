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
import com.example.intern_3days_hackathon.databinding.FragmentSavedEventBinding
import com.example.intern_3days_hackathon.databinding.FragmentSavedEventDetailBinding


class SavedEventDetailFragment : Fragment() {

    private val safeArgs: SavedEventDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activity = requireActivity()
        val actionBar = activity.actionBar
        actionBar?.setTitle(R.string.event_list_view_detail)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSavedEventDetailBinding.inflate(layoutInflater, container, false)
        val webView = binding.webView
        webView.webViewClient
        webView.loadUrl(safeArgs.url)

        return binding.root
    }


}
