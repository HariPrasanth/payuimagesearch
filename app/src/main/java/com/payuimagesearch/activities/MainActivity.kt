package com.payuimagesearch.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.payuimagesearch.BaseView
import com.payuimagesearch.R
import com.payuimagesearch.adapters.ImageAdapter
import com.payuimagesearch.apilayer.responsemodel.PixImage
import com.payuimagesearch.databinding.ActivityMainBinding
import com.payuimagesearch.util.EndlessRecyclerViewScrollListener
import com.payuimagesearch.util.ToastUtils
import com.payuimagesearch.viewmodels.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseView {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ImagesViewModel>()
    private lateinit var imagesAdapter: ImageAdapter
    lateinit var images: ArrayList<PixImage>
    var pageNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        val layoutManager = LinearLayoutManager(this)
        binding.rvImages.layoutManager = layoutManager

        images = ArrayList()
        imagesAdapter =
            ImageAdapter(
                this,
                images
            )
        binding.rvImages.adapter = imagesAdapter

        binding.bSearch.setOnClickListener {
            when {
                binding.etSearch.text.toString().isEmpty() -> ToastUtils.showErrorToast(
                    this,
                    getString(R.string.query_empty)
                )
                else -> {
                    viewModel.getImages("21828675-73538776f3bf54d088e9e7cf8", binding.etSearch.text.toString(), "photo", pageNumber,this)
                }
            }
        }

        viewModel.getImagesLiveData.observe(this, Observer {
            if(pageNumber == 1){
                if(it.hits.isNotEmpty()) {
                    images.clear()
                    binding.rvImages.visibility = View.VISIBLE
                    binding.empty.visibility = View.GONE
                    images.addAll(it.hits)
                    imagesAdapter.notifyDataSetChanged()
                } else {
                    binding.rvImages.visibility = View.GONE
                    binding.empty.visibility = View.VISIBLE
                }
            } else {
                if(it.hits.isNotEmpty()) {
                    binding.empty.visibility = View.GONE
                    images.addAll(it.hits)
                    imagesAdapter.notifyDataSetChanged()
                }
            }
        })

        val listener = object : EndlessRecyclerViewScrollListener(2, layoutManager), BaseView {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                pageNumber += 1
                viewModel.getImages("21828675-73538776f3bf54d088e9e7cf8", binding.etSearch.text.toString(), "photo", pageNumber,this)
            }

            override fun showProgress() {

            }

            override fun showServerError(errorString: String) {

            }

            override fun showUserError(errorString: String) {

            }

            override fun hideProgress() {

            }

            override fun hideError() {

            }

            override fun unauthorizedQuitApp() {

            }

            override fun objectNotFound(message: String) {

            }
        }
        binding.rvImages.addOnScrollListener(listener)
    }

    override fun showProgress() {
        binding.pb.visibility = View.VISIBLE
    }

    override fun showServerError(errorString: String) {
        ToastUtils.showErrorToast(
            this,
            errorString
        )
    }

    override fun showUserError(errorString: String) {
        ToastUtils.showErrorToast(
            this,
            errorString
        )
    }

    override fun hideProgress() {
        binding.pb.visibility = View.GONE
    }

    override fun hideError() {

    }

    override fun unauthorizedQuitApp() {

    }

    override fun objectNotFound(message: String) {
        ToastUtils.showErrorToast(
            this,
            message
        )
    }
}