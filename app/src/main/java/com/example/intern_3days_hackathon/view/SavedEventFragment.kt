package com.example.intern_3days_hackathon.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.data.EventDatabase
import com.example.intern_3days_hackathon.data.SavedEvent
import com.example.intern_3days_hackathon.databinding.FragmentSavedEventBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class SavedEventFragment : Fragment() {

    private lateinit var savedEventViewModel: SavedEventViewModel
    private lateinit var listAdapter: SavedEventListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentSavedEventBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_saved_event, container, false)

        // viewModelの初期化
        val application = requireNotNull(this.activity).application
        val dataSource = EventDatabase.getInstance(application).savedEventDao
        val viewModelFactory = SavedEventViewModelFactory(dataSource, application)

        savedEventViewModel = ViewModelProvider(this, viewModelFactory).get(SavedEventViewModel::class.java)

        // DBにイベント保存(仮)todo

        val inputButton = binding.inputEventButton
        inputButton.setOnClickListener {
            val eventName = binding.eventNameEdit.text.toString()
            val newEvent = SavedEvent(title = eventName)
            savedEventViewModel.insertEvent(newEvent)
            Log.i("test", newEvent.title)
        }

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
                            val action = SavedEventFragmentDirections.actionSavedEventFragmentToSavedEventDetailFragment(savedEvent.url)
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