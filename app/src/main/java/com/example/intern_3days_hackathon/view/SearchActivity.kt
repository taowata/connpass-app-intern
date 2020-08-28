package com.example.intern_3days_hackathon.view

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.intern_3days_hackathon.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.setupWithNavController(Navigation.findNavController(this, R.id.nav_fragment))

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.let {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val searchkey = query.toString()
                    Log.i("searchKey", "クエリは${searchkey}")

                    //searchFragment生成
                    showSearchFragment(searchkey)

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {


                    return true
                }
            })
        }
    }

    private fun showSearchFragment(searchkey: String) {
        val fragmentManager = supportFragmentManager
        fragmentManager?.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.nav_fragment, SearchFragment.newInstance(searchkey))
                    .addToBackStack(null)
                    .commit()
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        val button = view.findViewById<Button>(R.id.button)
////        button.setOnClickListener {
////            view?.let {
////                val searchKey  = it.editText.text.toString()
////                EventListRepository.listArticle(PAGE, searchKey).observe(viewLifecycleOwner, Observer { qiitaListResponse: ArrayList<Event> ->
////                    showEventListFragment(qiitaListResponse)
////                })
////            }
////        }
//
//
//    }
}
