package com.example.wallpaperapplication.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapplication.MyApplication
import com.example.wallpaperapplication.R
import com.example.wallpaperapplication.data.model.WallpaperImages
import com.example.wallpaperapplication.presentation.adapter.MainAdapter
import com.example.wallpaperapplication.presentation.main.MainActivity
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.*
import javax.inject.Inject


class MainFragment : Fragment() {

    private lateinit var mainFragmentComponent: MainFragmentComponent

    private var adapter: MainAdapter? = null

    @Inject
    lateinit var viewModel: MainFragmentViewModel

    private lateinit var gridLayoutManager: GridLayoutManager
    private var page = 1
    private var limit=26
    private lateinit var view1:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mainFragmentComponent = (activity?.application as MyApplication).appComponent.mainFragmentComponent().create()
        mainFragmentComponent.inject(this)
        view1 = inflater.inflate(R.layout.fragment_main, container, false)
        init()
        return view1;
    }

    private fun init(){
        setupAdapter()
        setupClickListener()
        attachObserver()
        getWallpaperImages()

    }

    private fun setupAdapter(){
        adapter = MainAdapter(requireContext())
        view1.rv.adapter = adapter
        gridLayoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        view1.rv.layoutManager=gridLayoutManager
    }

    private fun getWallpaperImages(){
        showProgress()
        viewModel.getWallpaperImages(MainActivity.category.toLowerCase(Locale.ROOT),page)
    }

    private fun attachObserver(){
        viewModel.wallpaperImages.observeForever {
            hideProgress()
            if (it != null) {
                Log.e("data", it.toString())
                adapter?.addData(it as ArrayList<WallpaperImages>)
            } else {
                Toast.makeText(activity, "Error getting data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupClickListener() {
        view1.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                Log.e("MainActivity", "onScrollChange: ")
                val visibleItemCount = gridLayoutManager.childCount
                val pastVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()
                val total = adapter?.itemCount
                if (page < limit) {
                    if (visibleItemCount + pastVisibleItem >= total!!) {
                        page++
                        Log.e("page", page.toString())
                        getWallpaperImages()
                    }

                } else {
                    Toast.makeText(context, "Loaded entire data", Toast.LENGTH_SHORT)
                            .show()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun showProgress() {
        view1.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        view1.progressBar.visibility = View.GONE
    }
}