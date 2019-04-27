package com.etatech.photosbrowser

import android.os.AsyncTask
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class DownloadJsonData : AsyncTask<String,Void,String>() {

    private val TAG = "DownloadJsonData"
    private var downloadStatus = DownloadStatus.IDLE

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

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }
}