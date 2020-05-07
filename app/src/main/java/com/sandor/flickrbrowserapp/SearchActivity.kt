package com.sandor.flickrbrowserapp

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView

private const val TAG = "SearchActivity"

class SearchActivity : BaseActivity() {
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activateToolBar(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search,menu)
        return true
    }
}
