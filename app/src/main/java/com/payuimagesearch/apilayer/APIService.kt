package com.payuimagesearch.apilayer

import com.payuimagesearch.apilayer.APINetworkLayer.Companion.API_VERSION_1
import com.payuimagesearch.apilayer.responsemodel.ImagesResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    companion object{
        const val GET_IMAGES = ""
    }

    @GET(API_VERSION_1 + GET_IMAGES)
    fun getImages(@Query("key") key: String, @Query("q") q: String, @Query("image_type") image_type: String) : Observable<Response<ImagesResponse>>
}