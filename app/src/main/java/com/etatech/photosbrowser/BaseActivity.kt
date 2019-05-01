package com.etatech.photosbrowser

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View


/****************************************************
 *** Created by Fady Fouad on 01/05/2019 at 21:44.***
 ***************************************************/

internal const val FLICKR_QUERY = "FLICKR_QUERY"
internal const val PHOTO_TRANSFER = "PHOTO_TRANSFER"

open class BaseActivity : AppCompatActivity() {
    private val TAG = "BaseActivity"

    internal fun activateToolBar(enableHome:Boolean){
        var toolbar = findViewById<View>(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)

    }

}