package com.payuimagesearch.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.payuimagesearch.BaseView
import com.payuimagesearch.apilayer.APINetworkLayer
import com.payuimagesearch.apilayer.ErrorProcessor
import com.payuimagesearch.apilayer.responsemodel.ImagesResponse
import io.reactivex.disposables.Disposable
import retrofit2.Response

class ImagesViewModel @ViewModelInject constructor(private val networkLayer: APINetworkLayer, private var errorProcessor: ErrorProcessor): ViewModel() {

    lateinit var getImagesDisposable: Disposable
    var getImagesLiveData: MutableLiveData<ImagesResponse> = MutableLiveData()

    fun getImages(key: String, q: String, image_type: String, baseView: BaseView) {
        baseView.showProgress()
        getImagesDisposable = networkLayer.getImages(key, q, image_type, baseView)
            .subscribe({ response: Response<ImagesResponse> ->
                if (response.isSuccessful) {
                    getImagesLiveData.value = response.body()
                } else {
                    errorProcessor.processApiError(response, baseView)
                }
                baseView.hideProgress()
                getImagesDisposable.dispose()
            }, { e ->
                baseView.showServerError("Something went wrong. please try after sometime...")
                e.printStackTrace()
            })
    }
}