package com.sandor.flickrbrowserapp

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

private const val TAG = "BaseActivity"
internal const val FLICKR_QUERY = "FLICKER_QUERY"
internal const val PHOTO_TRANSFER = "PHOTO_TRANSFER"

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {

    internal fun activateToolBar(enableHome: Boolean) {
        Log.d(TAG, "activateToolBar: called")
        var toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)

    }

}