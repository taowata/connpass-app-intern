package com.example.intern_3days_hackathon.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.databinding.FragmentUserBinding


class UserFragment : Fragment() {

    private lateinit var userInformationViewModel: UserInformationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val activity = requireActivity()
        val searchView = activity.findViewById<SearchView>(R.id.searchView)
        val linearLayout = activity.findViewById<LinearLayout>(R.id.linearLayout)
        searchView.visibility = View.GONE
        linearLayout.visibility = View.GONE

        val binding = FragmentUserBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userInformationViewModel = ViewModelProvider(this).get(UserInformationViewModel::class.java)

        userInformationViewModel.userName.observe(viewLifecycleOwner, Observer {
            val userNameContainer = view.findViewById<TextView>(R.id.user_name_container)
            userNameContainer.text = it
        })

        userInformationViewModel.favWord1.observe(viewLifecycleOwner, Observer {
            val favWordContainer1 = view.findViewById<TextView>(R.id.fav_words_container1)
            favWordContainer1.text = it
        })

        userInformationViewModel.favWord2.observe(viewLifecycleOwner, Observer {
            val favWordContainer2 = view.findViewById<TextView>(R.id.fav_words_container2)
            favWordContainer2.text = it
        })

        userInformationViewModel.favWord3.observe(viewLifecycleOwner, Observer {
            val favWordContainer3 = view.findViewById<TextView>(R.id.fav_words_container3)
            favWordContainer3.text = it
        })

        userInformationViewModel.myPageUrl.observe(viewLifecycleOwner, Observer {
            val myPageUrlContainer = view.findViewById<TextView>(R.id.my_page_url_container)
            myPageUrlContainer.text = it
        })

        val userNameContainer = view.findViewById<TextView>(R.id.user_name_container)
        userNameContainer.setOnClickListener {
            showDialogFragment(UserInformationViewModel.KEY_USER_NAME, userNameContainer.text.toString())
        }

        val favWordContainer1 = view.findViewById<TextView>(R.id.fav_words_container1)
        favWordContainer1.setOnClickListener {
            showDialogFragment(UserInformationViewModel.KEY_FAV_WORD1, favWordContainer1.text.toString())
        }

        val favWordContainer2 = view.findViewById<TextView>(R.id.fav_words_container2)
        favWordContainer2.setOnClickListener {
            showDialogFragment(UserInformationViewModel.KEY_FAV_WORD2, favWordContainer2.text.toString())
        }

        val favWordContainer3 = view.findViewById<TextView>(R.id.fav_words_container3)
        favWordContainer3.setOnClickListener {
            showDialogFragment(UserInformationViewModel.KEY_FAV_WORD3, favWordContainer3.text.toString())
        }

        val myPageUrlContainer = view.findViewById<TextView>(R.id.my_page_url_container)
        myPageUrlContainer.setOnClickListener {
            showDialogFragment(UserInformationViewModel.KEY_MY_PAGE_URL, myPageUrlContainer.text.toString())
        }

    }

    private fun showDialogFragment(key: String, value: String) {
        val newFragment = UserEditDialogFragment.newInstance(key, value)
        newFragment.show(childFragmentManager, "edit_dialog")
    }
}