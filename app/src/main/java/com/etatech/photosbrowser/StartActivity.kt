package com.etatech.photosbrowser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.content_start.*


class StartActivity : BaseActivity(),
    OnDaownloadComplete ,
    DownloadFlickerData.OnDataAvailable ,
    RVItemClickLisener.OnItemClick{

    //    companion object { //static in JAVA
    private val TAG = "StartActivity"
//    }
    var photos : MutableList<Photo> = ArrayList()
    val adapter = PhotoAdapter(this,photos)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        activateToolBar(false)
        Log.d(TAG,"onCreate Called")

        val downloadJsonData = DownloadJsonData(this)
//        downloadJsonData.onDownloadCompleteListener(this)
        val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne","Android","en-us",true)
        try {
            downloadJsonData.execute("https://api.flickr.com/services/feeds/photos_public.gne?tags=android&format=json&nojsoncallback=1")
        }catch (e:Exception){
            Log.d(TAG,e.message)
        }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        photos_recyclerView.layoutManager = LinearLayoutManager(this)
        photos_recyclerView.adapter=adapter
        photos_recyclerView.addOnItemTouchListener(RVItemClickLisener(this,photos_recyclerView,this))
        photos_recyclerView.hasFixedSize()

    }

    override fun onResume() {
        super.onResume()
        val sharPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val query = sharPref.getString(FLICKR_QUERY,"")
        if (query.isNotEmpty()){
            val downloadJsonData = DownloadJsonData(this)
            val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne",query,"en-us",true)
            try {
                downloadJsonData.execute("https://api.flickr.com/services/feeds/photos_public.gne?tags=$query&format=json&nojsoncallback=1")
            }catch (e:Exception){
                Log.d(TAG,e.message)
            }
        }
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d(TAG,"onItemClick Called")
        Toast.makeText(this,"on Click ",Toast.LENGTH_SHORT).show()
        val photo = adapter.getPhoto(position)
        if (photo != null ){
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra(PHOTO_TRANSFER,photo)
            startActivity(intent)
        }

    }

    override fun onItemLongClick(view: View, position: Int) {
        Log.d(TAG,"onItemLongClick Called")
        Toast.makeText(this,"on Long Click ",Toast.LENGTH_SHORT).show()
    }

    private fun createUri(url: String, search: String,lang : String ,isMatched: Boolean):String{
        Log.d(TAG,"createUri -> ")
        var uri = Uri.parse(url)
            .buildUpon()
            .appendQueryParameter("tags",search)
            .appendQueryParameter("tagmode",if (isMatched)"ALL" else "ANY")
            .appendQueryParameter("lang",lang)
            .appendQueryParameter("format","json")
            .appendQueryParameter("nojsoncallback","1")
            .build()
            .toString()
        Log.d(TAG,"Url -> $uri")
        return uri
    }
    override fun onDownloadComplete (data:String, status:DownloadStatus){

        if (status == DownloadStatus.OK){
            Log.d(TAG,"onDownloadComplete -> $data")
            val downloadData = DownloadFlickerData(this)
            downloadData.execute(data)
        }else{
            Log.d(TAG,"Faild To Download $data status -> $status")
        }

    }
    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG,"onDataAvailable Called ->$data")
        adapter.loadNewPhoto(data)
    }
    override fun OnError(e: java.lang.Exception) {
        Log.d(TAG,"OnError Called -> ${e.message}")

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_start,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.search -> {
                startActivity(Intent(this,SearchActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
