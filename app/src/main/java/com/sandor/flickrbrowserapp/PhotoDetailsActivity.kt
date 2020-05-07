package com.sandor.flickrbrowserapp

import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_photo_details.*

class PhotoDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        activateToolBar(true)

        val photo = intent.getSerializableExtra(PHOTO_TRANSFER) as Photo
        photo_title.text = resources.getText(R.string.photo_title_text, photo.title)
        photo_author.text = photo.author
        photo_tags.text = resources.getText(R.string.photo_tags_text, photo.tags)

        Picasso.get()
            .load(photo.link)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(photo_image)
    }

}
