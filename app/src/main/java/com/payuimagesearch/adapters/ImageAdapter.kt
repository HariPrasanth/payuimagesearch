package com.payuimagesearch.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.payuimagesearch.R
import com.payuimagesearch.activities.ImageActivity
import com.payuimagesearch.apilayer.responsemodel.PixImage


class ImageAdapter(
    val context: Context,
    val images: ArrayList<PixImage>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        return BannerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as BannerViewHolder).setData(images[position])
    }

    internal inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val iv_image: ImageView = itemView.findViewById(R.id.iv_image)
        private val parent: FrameLayout = itemView.findViewById(R.id.parent)

        fun setData(image: PixImage) {
            Glide.with(context)
                .load(image.largeImageURL)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(iv_image)

            parent.setOnClickListener {
                val intent = Intent(context, ImageActivity::class.java)
                intent.putExtra("image_url", image.webformatURL)
                context.startActivity(intent)
            }
        }
    }
}