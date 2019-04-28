package com.etatech.photosbrowser

import android.os.AsyncTask
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

private val TAG = "DownloadFlickerData"

class DownloadFlickerData(private val listener:OnDataAvailable): AsyncTask<String, Void, ArrayList<Photo>>() {

    interface OnDataAvailable{
        fun onDataAvailable(data:List<Photo>)
        fun OnError(e:Exception)
    }
    override fun doInBackground(vararg params: String?): ArrayList<Photo> {
        Log.d(TAG,"doInBackground Started")
        var photoList = ArrayList<Photo>()
        try {
            val jasonData = JSONObject(params[0])
            val itemArray = jasonData.getJSONArray("items")
            for(i in 0 until itemArray.length()){
                val jasonPhoto = itemArray.getJSONObject(i)
                val title = jasonPhoto.getString("title")
                val author = jasonPhoto.getString("author")
                val published = jasonPhoto.getString("published")
                val author_id = jasonPhoto.getString("author_id")
                val tags = jasonPhoto.getString("tags")
                val jasonMedia = jasonPhoto.getJSONObject("media")
                val photoUrl = jasonMedia.getString("m")
                val link = photoUrl.replaceFirst("_m.jpg","_b.jpg")
                val photoObject = Photo(title,author,author_id,published,link,tags,link)

                photoList.add(photoObject)

                Log.d(TAG,"Photos $photoObject")
            }
        }catch (e:JSONException){
            Log.d(TAG,e.message)
            listener.OnError(e)
        }
        Log.d(TAG,"doInBackground ended")
        return photoList
    }

    override fun onPostExecute(result: ArrayList<Photo>) {
        Log.d(TAG,"onPostExecute Called")
        listener.onDataAvailable(result)
    }
}