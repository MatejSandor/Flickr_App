package com.sandor.flickrbrowserapp

import android.net.Uri
import android.nfc.NdefRecord.createUri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), GetRawData.OnDownloadComplete,
    GetFlickrJsonData.OnDataAvailable, RecyclerItemClickListener.OnRecyclerClickListener {

    private val flickrRecyclerViewAdapter = FlickrRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = flickrRecyclerViewAdapter

        val url = createUri(
            "https://www.flickr.com/services/feeds/photos_public.gne",
            "android,oreo",
            "en-us",
            true
        )
        val getRawData = GetRawData(this)
        getRawData.execute(url)

        Log.d(TAG, "onCreate: ends here")
    }

    private fun createUri(
        baseUrl: String,
        searchCriteria: String,
        language: String,
        matchAll: Boolean
    ): String {
        Log.d(TAG, "createUri: called")
        return Uri.parse(baseUrl)
            .buildUpon()
            .appendQueryParameter("tags", searchCriteria)
            .appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
            .appendQueryParameter("lang", language)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .build().toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG, "onCreateOptionsMenu: called")
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected: called")
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        val getFlickrJsonData = GetFlickrJsonData(this)
        getFlickrJsonData.execute(data)
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete: called")
        } else {
            Log.d(TAG, "onDownloadComplete: failed with status $status. Error message is $data")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG, "onDataAvailable: called")
        flickrRecyclerViewAdapter.loadNewData(data)
    }

    override fun onError(exception: Exception) {
        Log.e(TAG, "Exception ${exception.message}")
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d(TAG, "onItemClick: called")
        Toast.makeText(this,"button was tapped",Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
        Log.d(TAG, "onItemLongClick: called")
        Toast.makeText(this,"button was tapped for longer!",Toast.LENGTH_LONG).show()
    }
}
