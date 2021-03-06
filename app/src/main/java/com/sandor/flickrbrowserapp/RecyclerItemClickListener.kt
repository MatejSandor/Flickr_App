package com.sandor.flickrbrowserapp

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "RecyclerItemClickListen"

class RecyclerItemClickListener(context: Context, recyclerView: RecyclerView, private val listener: OnRecyclerClickListener)
    : RecyclerView.SimpleOnItemTouchListener() {

    interface OnRecyclerClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    private val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG, "onSingleTapUp: called")
            val childView = recyclerView.findChildViewUnder(e.x,e.y)
            return if (childView != null) {  // Added 2018-12-18
                Log.d(TAG, ".onSingleTapUp calling listener.onItemClick")
                listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
                true
            } else {
                false
            }
        }

        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG, "onLongPress: called")
            val childView = recyclerView.findChildViewUnder(e.x,e.y)
            if (childView != null) {  // Added 2018-12-18
                Log.d(TAG, ".onLongPress calling listener.onItemClick")
                listener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView))
            }
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, "onInterceptTouchEvent: called")
        val result = gestureDetector.onTouchEvent(e)
        return result
    }
}