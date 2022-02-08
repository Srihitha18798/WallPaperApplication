package com.example.wallpaperapplication.presentation.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapplication.data.model.WallpaperImages
import com.example.wallpaperapplication.databinding.ListItemBinding
import com.example.wallpaperapplication.presentation.detail.DetailActivity
import com.example.wallpaperapplication.presentation.utility.loadImage
import kotlinx.android.synthetic.main.list_item.view.*

class MainAdapter(private val context: Context) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    private val wallpaperImages: MutableList<WallpaperImages> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                ListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    init {
        list = this.wallpaperImages
    }

    companion object {
        var list: MutableList<WallpaperImages> = arrayListOf()
    }

    override fun getItemCount(): Int {
        return wallpaperImages.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem: WallpaperImages = wallpaperImages[position]
        /*Glide.with(holder.binding.image.context).load(currentItem.previewURL).apply(RequestOptions().override(1000,1000))
            .into(holder.binding.image)*/
        holder.binding.image.loadImage(currentItem.largeImageURL)
        holder.binding.image.setOnClickListener {
            Log.e("position", position.toString())
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }

    }

    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = itemView.image
    }

    fun clear() {
        val oldSize = wallpaperImages.size
        wallpaperImages.clear()
        notifyItemRangeRemoved(0, oldSize)
    }

    fun setData(list: MutableList<WallpaperImages>) {
        this.wallpaperImages.addAll(list)
        notifyItemRangeInserted(0, wallpaperImages.size)
    }

    fun addData(list: ArrayList<WallpaperImages>) {
        val size = this.wallpaperImages.size
        this.wallpaperImages.addAll(list)
        val sizeNew = this.wallpaperImages.size
        notifyItemRangeChanged(size, sizeNew)
    }
}