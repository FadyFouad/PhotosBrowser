package com.etatech.photosbrowser

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class DetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        activateToolBar(true)
    }
}
