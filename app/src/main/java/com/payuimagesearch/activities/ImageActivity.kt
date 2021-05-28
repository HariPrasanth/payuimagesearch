package com.payuimagesearch.activities

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.payuimagesearch.R
import com.payuimagesearch.databinding.ActivityImageBinding


class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding
    private lateinit var imageUrl : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_image
        )
        imageUrl = intent.getStringExtra("image_url").toString()
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(binding.ivImage)

//        Glide
//            .with(this)
//            .load(imageUrl)
//            .placeholder(R.drawable.placeholder)
//            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(if (true) 32 else 1)))
//            .into(object : CustomTarget<Drawable>(200, 200) {
//                override fun onLoadCleared(placeholder: Drawable?) {
//                    // called when imageView is cleared. If you are referencing the bitmap
//                    // somewhere else too other than this imageView clear it here
//                }
//
//                override fun onResourceReady(
//                    resource: Drawable,
//                    transition: Transition<in Drawable>?
//                ) {
//                    binding.ivImage.setImageDrawable(resource)
//                }
//            })
    }
}