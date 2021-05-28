package com.payuimagesearch.apilayer

import com.payuimagesearch.BaseView
import com.payuimagesearch.apilayer.responsemodel.ImagesResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class APINetworkLayer @Inject constructor(var apiService: APIService, var errorProcessor : ErrorProcessor){

    companion object {
        const val API_VERSION_1 = ""
    }

    fun getImages(key: String, q: String, image_type: String, baseView: BaseView): Observable<Response<ImagesResponse>> {
        return apiService.getImages(key, q, image_type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { t -> errorProcessor.showError(baseView, t) }
    }
}