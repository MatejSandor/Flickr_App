package com.sandor.flickrbrowserapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), GetRawData.OnDownloadComplete,
    GetFlickrJsonData.OnDataAvailable {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val getRawData = GetRawData(this)
        getRawData.execute("https://www.flickr.com/services/feeds/photos_public.gne?/tag=android,oreo,sdk&tagmode=any&format=json&nojsoncallback=1")

        Log.d(TAG, "onCreate: ends here")
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
    }

    override fun onError(exception: Exception) {
        Log.e(TAG, "Exception ${exception.message}")
    }

}
