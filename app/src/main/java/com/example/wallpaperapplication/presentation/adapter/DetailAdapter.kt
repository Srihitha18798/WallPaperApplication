package com.example.wallpaperapplication.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.wallpaperapplication.R
import com.example.wallpaperapplication.data.model.WallpaperImages
import com.example.wallpaperapplication.presentation.utility.loadImage

class DetailAdapter(
        private val context: Context,
        private val imageList: ArrayList<WallpaperImages> = arrayListOf()
) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val v: View = inflater.inflate(R.layout.viewpager_layout, null)
        val myImageView: ImageView = v.findViewById(R.id.image)
        myImageView.loadImage(imageList[position].largeImageURL)
        if (position == 0) {
            selectedImage(imageList[position].largeImageURL)
        } else {
            selectedImage(imageList[position - 1].largeImageURL)
        }
        val vp = container as ViewPager
        vp.addView(v, 0)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val v: View = `object` as View
        viewPager.removeView(v)
    }

    private fun selectedImage(url: String) {
        val intent = Intent("custom-message")
        intent.putExtra("urlPath", url)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }

    private fun AppCompatImageView?.loadImage(
            image: Any,
            placeholder: Int? = R.drawable.icn_placeholder
    ) {
        Glide.with(context)
                .load(image)
                .placeholder(placeholder!!)
                .into(this!!)
    }

}