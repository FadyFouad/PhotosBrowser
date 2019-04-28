package com.etatech.photosbrowser

class Photo (val title:String ,val author :String ,val author_id:String ,val published :String ,val link:String ,val tags:String,val m:String) {

    override fun toString(): String {
        return "Photo(title='$title', author='$author', author_id='$author_id', 'published = $published' ,link='$link', tags='$tags', m='$m')"
    }
}