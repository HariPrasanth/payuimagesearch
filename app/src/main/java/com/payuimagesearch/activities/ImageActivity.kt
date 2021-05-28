package com.payuimagesearch.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
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
    }
}