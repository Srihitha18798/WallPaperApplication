package com.example.wallpaperapplication.presentation.detail

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.MimeTypeMap
import android.webkit.URLUtil
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.viewpager.widget.ViewPager
import com.example.wallpaperapplication.MyApplication
import com.example.wallpaperapplication.R
import com.example.wallpaperapplication.data.model.WallpaperImages
import com.example.wallpaperapplication.databinding.ActivityDetailBinding
import com.example.wallpaperapplication.presentation.adapter.DetailAdapter
import com.example.wallpaperapplication.presentation.adapter.MainAdapter
import com.example.wallpaperapplication.presentation.main.MainActivity
import com.example.wallpaperapplication.presentation.utility.OnSwipeTouchListener
import com.example.wallpaperapplication.presentation.utility.isNetworkAvailable
import com.example.wallpaperapplication.presentation.utility.loadImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class DetailActivity : AppCompatActivity() {

    private lateinit var detailComponent: DetailComponent

    private lateinit var binding: ActivityDetailBinding

    private lateinit var urlPath: String

    private var position: Int = 0
    private var adapter: DetailAdapter? = null


    private var list: List<WallpaperImages> = arrayListOf()
    private val STORAGE_PERMISSION_CODE: Int = 1000

    private lateinit var viewPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        detailComponent = (application as MyApplication).appComponent.detailComponent().create()
        detailComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbarTitle("Detail")
        init()
    }

    private fun init() {
        viewPager = findViewById(R.id.view_pager)
        if (intent.extras?.containsKey("position") == true) {
            position = intent.extras?.getInt("position")!!
            Log.e("position from adapter", position.toString())
        }
        list = MainAdapter.list
        setupAdapter()
        setupClickListener()
    }

    private fun setupClickListener() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver,
                IntentFilter("custom-message")
        )
        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setupAdapter() {
        adapter = DetailAdapter(this, list as ArrayList<WallpaperImages>)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(position, true)
    }

    var mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            urlPath = intent.getStringExtra("urlPath").toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.Save) {
            Log.e("save", "Save Option")
            Log.e("url", urlPath)
            if (applicationContext.isNetworkAvailable() == false) {
                Toast.makeText(this, "Internet is not connected", Toast.LENGTH_LONG).show()
            } else {
                saveImage()
            }
            return true
        } else if (item.itemId == R.id.wallpaper) {
            Log.e("wallpaper", "Wallpaper Option")
            Log.e("url", urlPath)
            if (applicationContext.isNetworkAvailable() == false) {
                Toast.makeText(this, "Internet is not connected", Toast.LENGTH_LONG).show()
            } else {
                setAsWallpaper(urlPath)
            }

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setAsWallpaper(url: String) {
        GlobalScope.launch {
            //val result: Bitmap = Picasso.with(applicationContext).load(url).resize(3000, 5000).get()
            val result: Bitmap = Picasso.with(applicationContext).load(url).get()
            val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(applicationContext)
            try {
                wallpaperManager.setBitmap(result)
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
        Toast.makeText(this, "Successfully set wallpaper", Toast.LENGTH_SHORT).show()
    }

    private fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun saveImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(
                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        STORAGE_PERMISSION_CODE
                )
                saveImage()
            } else {
                startDownload(urlPath)
            }
        } else {
            startDownload(urlPath)
        }
    }

    private fun startDownload(url: String) {
        val fileName = URLUtil.guessFileName(url, null, MimeTypeMap.getFileExtensionFromUrl(url))
        Log.d("Detail Activity now", fileName)
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle(fileName)
        request.setDescription("The File is Downoading...")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                uri.lastPathSegment
        )
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
        Toast.makeText(this, "Successfully downloaded wallpaper", Toast.LENGTH_SHORT).show()

    }

}