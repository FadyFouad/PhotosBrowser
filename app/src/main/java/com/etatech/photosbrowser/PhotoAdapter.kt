package com.etatech.photosbrowser

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.FieldPosition

/****************************************************
 **Created by Fady Fouad on 2019-04-30 at 07:20 PM.**
 ***************************************************/

class PhotoAdapter (var context: Context,var photos : List<Photo>  ) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    private val TAG = "PhotoAdapter"//TODO Edit TAG name

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PhotoAdapter.PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(context).inflate(R.layout.photo_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoAdapter.PhotoViewHolder, position: Int) {
        val photo = photos[position]
        val photoUrl = photo.link
        Log.d(TAG,"URL -> $photoUrl")
        Picasso.get()
            .load(photoUrl)
            .placeholder(R.drawable.ic_broken_image)
            .into(holder.photo)

        holder.photoName.setText(photo.title)
        holder.photoPub.setText(photo.author)


    }
    fun loadNewPhoto(newPhoto:List<Photo>){
        photos = newPhoto
        notifyDataSetChanged()

    }

    fun getPhoto(position:Int):Photo?{
        return if (photos.isNotEmpty()){
            photos[position]
        }else null
    }


    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val TAG = "PhotoViewHolder"
        val photo :ImageView = view.findViewById(R.id.photo_item)
        val photoName :TextView = view.findViewById(R.id.photo_name_item)
        val photoPub :TextView = view.findViewById(R.id.photo_pub_item)
    }
}