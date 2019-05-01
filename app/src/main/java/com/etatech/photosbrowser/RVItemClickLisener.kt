package com.etatech.photosbrowser

import android.content.Context
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import java.text.FieldPosition


/****************************************************
 ***Created by Fady Fouad on 01/05/2019 at 19:00.***
 ***************************************************/
class RVItemClickLisener (context : Context, reclerView:RecyclerView,private val lisener:OnItemClick):
    RecyclerView.SimpleOnItemTouchListener(){

    val TAG ="RVItemClickLisener"
    interface OnItemClick{
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    private val getGestureDetector = GestureDetectorCompat(context,object:GestureDetector.SimpleOnGestureListener(){
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val childView = reclerView.findChildViewUnder(e.x,e.y)
            if (childView != null) {
                lisener.onItemClick(childView,reclerView.getChildAdapterPosition(childView))
            }
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            val childView = reclerView.findChildViewUnder(e.x,e.y)
            if (childView != null) {
                lisener.onItemLongClick(childView,reclerView.getChildAdapterPosition(childView))
            }
            super.onLongPress(e)
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG,"onInterceptTouchEvent $e")
        val result = getGestureDetector.onTouchEvent(e)
        return result
    }
}