package com.sandor.flickrbrowserapp

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus() {
    OK,IDLE, NOT_INITIALIZED, FAILED_OR_EMPTY, PERMISSIONS_ERROR, ERROR
}

private const val TAG = "GetRawData"

class GetRawData : AsyncTask<String, Void, String>() {
    private var downloadStatus = DownloadStatus.IDLE

    override fun doInBackground(vararg params: String?): String {
        if(params[0] == null) {
            downloadStatus = DownloadStatus.NOT_INITIALIZED
            return "No URL specified"
        }
        try {
            downloadStatus = DownloadStatus.OK
            return URL(params[0]).readText()
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is MalformedURLException -> {
                    downloadStatus = DownloadStatus.NOT_INITIALIZED
                    return "MalformedURLException ${e.message}"
                }
                is IOException -> {
                    downloadStatus = DownloadStatus.FAILED_OR_EMPTY
                    return "IOException ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus = DownloadStatus.PERMISSIONS_ERROR
                    return "Security Exception ${e.message}"
                }
                else -> {
                    downloadStatus = DownloadStatus.ERROR
                    return "Unknown Exception ${e.message}"
                }
            }
            Log.e(TAG,errorMessage)
            return errorMessage
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }
}