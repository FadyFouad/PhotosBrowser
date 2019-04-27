package com.etatech.photosbrowser

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.Toast


private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    private var i = 0;
    private var time:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"onCreate called $time")

//        var starttime = System.currentTimeMillis()/60
//
//        if (i == 1){
//            Toast.makeText(this,"i = 1 ",Toast.LENGTH_SHORT).show()
//        }else if (i==2){
//            Toast.makeText(this,"i = 2 ",Toast.LENGTH_SHORT).show()
//        }else if (i==3){
//            Toast.makeText(this,"i = 3 ",Toast.LENGTH_SHORT).show()
//        }else if (i==4){
//            Toast.makeText(this,"i = 4 ",Toast.LENGTH_SHORT).show()
//        }else{
//            Toast.makeText(this,"i = 0 ",Toast.LENGTH_SHORT).show()
//        }
//        time = System.currentTimeMillis()/60
//        Log.d(TAG,"onCreate ended  $time")
//
    }
}
