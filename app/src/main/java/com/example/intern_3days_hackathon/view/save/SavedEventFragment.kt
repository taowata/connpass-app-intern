package com.example.intern_3days_hackathon.view.save

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.data.EventDatabase
import com.example.intern_3days_hackathon.databinding.FragmentSavedEventBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SavedEventFragment : Fragment() {

    private lateinit var savedEventViewModel: SavedEventViewModel
    private lateinit var listAdapter: SavedEventListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentSavedEventBinding.inflate(layoutInflater, container, false)

        val activity = requireActivity()
        val searchView = activity.findViewById<SearchView>(R.id.searchView)
        val linearLayout = activity.findViewById<LinearLayout>(R.id.linearLayout)
        searchView.visibility = View.GONE
        linearLayout.visibility = View.GONE

        // viewModelの初期化
        val application = activity.application
        val dataSource = EventDatabase.getInstance(application).savedEventDao
        val viewModelFactory = SavedEventViewModelFactory(dataSource, application)

        savedEventViewModel = ViewModelProvider(this, viewModelFactory).get(SavedEventViewModel::class.java)

        val recyclerView = binding.savedEventList
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        listAdapter = SavedEventListAdapter(
                SavedEventListener(
                        // 削除アイコンのアクション
                        { savedEvent ->
                            //　ダイアログ表示
                            context?.let {
                                MaterialAlertDialogBuilder(it)
                                        .setMessage("イベント\n${savedEvent.title}\n\nを削除しますか？")
                                        // 削除をやめる
                                        .setNegativeButton(resources.getString(R.string.cancel)) { _, _ -> }
                                        // 削除する
                                        .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                                            // 削除のアクション
                                            Toast.makeText(
                                                    context,
                                                    "${savedEvent.title}\n\nを削除しました",
                                                    Toast.LENGTH_LONG
                                            )
                                                    .show()

                                            savedEventViewModel.deleteEvent(savedEvent)
                                        }
                                        .show()
                            }

                        },

                        // 詳細画面への遷移アクション
                        { savedEvent ->
                            val action =
                                    SavedEventFragmentDirections.actionSavedEventFragmentToSavedEventDetailFragment(savedEvent.url)
                            view?.findNavController()?.navigate(action)
                        }
                )
        )

        recyclerView.adapter = listAdapter

        savedEventViewModel.eventList.observe(
                viewLifecycleOwner, { events ->
                    listAdapter.submitList(events)
                    recyclerView.adapter = listAdapter
            Log.i("test", events.toString())
        })

        return binding.root
    }
}