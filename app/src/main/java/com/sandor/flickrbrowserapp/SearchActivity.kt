package com.sandor.flickrbrowserapp

import android.os.Bundle

private const val TAG = "SearchActivity"

class SearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activateToolBar(true)
    }

}
