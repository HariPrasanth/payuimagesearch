package com.payuimagesearch.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.payuimagesearch.BaseView
import com.payuimagesearch.R
import com.payuimagesearch.databinding.ActivityMainBinding
import com.payuimagesearch.viewmodels.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseView {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ImagesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

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