package com.etatech.photosbrowser

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import javax.security.auth.callback.Callback

class DownloadJsonData(private val listener:OnDaownloadComplete) : AsyncTask<String,Void,String>() {

    private val TAG = "DownloadJsonData"
    private var downloadStatus = DownloadStatus.IDLE
//    private var listener : StartActivity? = null

    override fun doInBackground(vararg params: String?): String {

        if(params[0]==null){
            downloadStatus = DownloadStatus.NOT_INSIALIZED
            return "No Url"
        }
        try {
            downloadStatus = DownloadStatus.OK
            return URL(params[0]).readText()
        }catch (e : Exception){
            val errorMessage = when (e){
                is MalformedURLException -> {
                    downloadStatus =DownloadStatus.NOT_INSIALIZED
                    "Invaled URL ${e.message}"
                }is IOException -> {
                    downloadStatus =DownloadStatus.FAILED_OR_EMPTY
                    "Reading Data ${e.message}"
                }is SecurityException -> {
                    downloadStatus =DownloadStatus.PERMISSION_ERROR
                    "Need Permission ${e.message}"
                }else ->{
                    downloadStatus = DownloadStatus.ERROR
                    "Unkown Error"
                }
            }
            return errorMessage
        }
    }

    override fun onPostExecute(result: String) {
//        super.onPostExecute(result) Does not Do Any Thing
        Log.d(TAG,"onPostExecute Called -> $result")
        listener.onDownloadComplete(result,downloadStatus)

    }

//    fun onDownloadCompleteListener (callBackObject:StartActivity) {
//
//        listener = callBackObject
//
//    }

}