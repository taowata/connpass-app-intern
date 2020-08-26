package com.example.intern_3days_hackathon.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.intern_3days_hackathon.R


class UserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = context?.getSharedPreferences("user_information", Context.MODE_PRIVATE)
        val userName = sharedPref?.getString("username", "tao")
        val favWord1 = sharedPref?.getString("fav_word1", "Kotlin")
        val favWord2 = sharedPref?.getString("fav_word2", "Android")
        val favWord3 = sharedPref?.getString("fav_word3", "Python")
        val myPageUrl = sharedPref?.getString("my_page_url", "https://connpass.com/user/Tao130/")

        val userNameContainer = view.findViewById<TextView>(R.id.user_name_container)
        val favWordContainer1 = view.findViewById<TextView>(R.id.like_words_container1)
        val favWordContainer2 = view.findViewById<TextView>(R.id.like_words_container2)
        val favWordContainer3 = view.findViewById<TextView>(R.id.like_words_container3)
        val myPageUrlContainer = view.findViewById<TextView>(R.id.my_page_url_container)

        setTextToTextView(
                Pair(userNameContainer, userName!!),
                Pair(favWordContainer1, favWord1!!),
                Pair(favWordContainer2, favWord2!!),
                Pair(favWordContainer3, favWord3!!),
                Pair(myPageUrlContainer, myPageUrl!!),
        )
    }

    private fun setTextToTextView(vararg pairs: Pair<TextView, String>) {
        for(pair in pairs) {
            pair.first.text = pair.second
        }
    }

}