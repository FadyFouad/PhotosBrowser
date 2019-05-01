package com.etatech.photosbrowser

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        activateToolBar(true)

        val photo = intent.extras.getParcelable(PHOTO_TRANSFER) as  Photo
        photo_name_detail.text=photo.title
        Picasso
            .get()
            .load(photo.link)
            .placeholder(R.drawable.ic_broken_image)
            .into(photo_details)
        photo_pub_name_detail.text= photo.author
        photo_pub_at_detail.text= photo.published
    }
}
