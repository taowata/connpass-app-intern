package com.example.intern_3days_hackathon.view

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.intern_3days_hackathon.R
import com.example.intern_3days_hackathon.model.Event
import com.example.intern_3days_hackathon.view.search.EventListFragment
import com.example.intern_3days_hackathon.view.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

class SearchActivity : AppCompatActivity() {

    private var searchKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val navController = findNavController(this, R.id.nav_fragment)
        setupActionBarWithNavController(navController,
        AppBarConfiguration(
                setOf(R.id.searchFragment, R.id.savedEventFragment, R.id.userFragment, R.id.savedEventDetailFragment)
        ))

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.setupWithNavController(navController)

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.let {
            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchKey = query.toString()
                    val bundle = Bundle()
                    bundle.putString(SEARCH_KEY, searchKey)
                    navController.navigate(
                            R.id.searchFragment,
                            bundle
                    )
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

    companion object {
        private const val SEARCH_KEY = "searchKey"
    }
}
