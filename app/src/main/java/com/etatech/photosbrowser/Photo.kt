package com.etatech.photosbrowser

import android.os.Parcel
import android.os.Parcelable

class Photo (val title:String ,val author :String ,val author_id:String ,val published :String ,
             val link:String ,val tags:String,val m:String): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    //TODO Fix Serializable 650v & check speed of Serializable vs Parcelable
    override fun toString(): String {
        return "Photo(title='$title', author='$author', author_id='$author_id', 'published = $published' ,link='$link', tags='$tags', m='$m')"
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(author_id)
        parcel.writeString(published)
        parcel.writeString(link)
        parcel.writeString(tags)
        parcel.writeString(m)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}