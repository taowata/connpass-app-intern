package com.example.intern_3days_hackathon.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.data.EventDatabase
import com.example.intern_3days_hackathon.data.SavedEvent

class SavedEventFragment : Fragment() {

    private lateinit var savedEventViewModel: SavedEventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = EventDatabase.getInstance(application).savedEventDao

        // val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val viewModelFactory = SavedEventViewModelFactory(dataSource, application)

        savedEventViewModel = ViewModelProvider(this, viewModelFactory).get(SavedEventViewModel::class.java)


        val inputButton = view.findViewById<Button>(R.id.input_event_button)

        inputButton.setOnClickListener {
            val eventName = view.findViewById<EditText>(R.id.event_name_edit).text.toString()
            val newEvent = SavedEvent(title = eventName)
            savedEventViewModel.insertEvent(newEvent)
            Log.i("test", newEvent.title)
        }

    }
}