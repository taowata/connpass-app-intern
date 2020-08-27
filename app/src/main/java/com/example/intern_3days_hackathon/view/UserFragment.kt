package com.example.intern_3days_hackathon.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.intern_3days_hackathon.R


class UserFragment : Fragment() {

    private lateinit var userInformationViewModel: UserInformationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userInformationViewModel = ViewModelProvider(this).get(UserInformationViewModel::class.java)

        userInformationViewModel.userName.observe(viewLifecycleOwner, Observer {
            val userNameContainer = view.findViewById<TextView>(R.id.user_name_container)
            userNameContainer.text = it
        })

        userInformationViewModel.favWord1.observe(viewLifecycleOwner, Observer {
            val favWordContainer1 = view.findViewById<TextView>(R.id.like_words_container1)
            favWordContainer1.text = it
        })

        userInformationViewModel.favWord2.observe(viewLifecycleOwner, Observer {
            val favWordContainer2 = view.findViewById<TextView>(R.id.like_words_container2)
            favWordContainer2.text = it
        })

        userInformationViewModel.favWord3.observe(viewLifecycleOwner, Observer {
            val favWordContainer3 = view.findViewById<TextView>(R.id.like_words_container3)
            favWordContainer3.text = it
        })

        userInformationViewModel.myPageUrl.observe(viewLifecycleOwner, Observer {
            val myPageUrlContainer = view.findViewById<TextView>(R.id.my_page_url_container)
            myPageUrlContainer.text = it
        })

        val editIcon = view.findViewById<ImageView>(R.id.edit_icon_username)

        editIcon.setOnClickListener {
            showDialogFragment()
        }

    }

    private fun showDialogFragment() {
        val newFragment = UserEditDialogFragment()
        newFragment.show(childFragmentManager, "edit_dialog")
    }
}