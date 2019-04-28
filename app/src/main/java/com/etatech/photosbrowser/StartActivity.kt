package com.etatech.photosbrowser

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_start.*


class StartActivity : AppCompatActivity(),OnDaownloadComplete {

//    companion object { //static in JAVA
        private val TAG = "StartActivity"
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
//        setSupportActionBar(toolbar)
        Log.d(TAG,"onCreate Called")


        val downloadJsonData = DownloadJsonData(this)
//        downloadJsonData.onDownloadCompleteListener(this)
        downloadJsonData.execute("https://api.flickr.com/services/feeds/photos_public.gne?tags=android,oreo&format=json&nojsoncallback=1")



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onDownloadComplete (data:String, status:DownloadStatus){

        if (status == DownloadStatus.OK){
            Log.d(TAG,"onDownloadComplete -> $data")
        }else{
            Log.d(TAG,"Faild To Download $data status -> $status")
        }

    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_start,menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId){
//            R.id.test -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
