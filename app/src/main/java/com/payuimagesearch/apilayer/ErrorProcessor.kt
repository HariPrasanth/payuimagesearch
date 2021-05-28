package com.payuimagesearch.apilayer

import com.google.gson.Gson
import com.payuimagesearch.BaseView
import com.payuimagesearch.dagger.APIModule.Companion.RESPONSE_400
import com.payuimagesearch.dagger.APIModule.Companion.RESPONSE_401
import com.payuimagesearch.dagger.APIModule.Companion.RESPONSE_404
import com.payuimagesearch.dagger.APIModule.Companion.RESPONSE_500
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ErrorProcessor @Inject constructor(var gson : Gson) {

    fun showError(baseView : BaseView, e : Throwable) {
        baseView.hideProgress()
        var message : String? = e.message
        if (message!!.contains("google.com")) {
            message = "Please check your Internet Connection"
        } else {
            message = "Something went wrong!"
        }
        e.printStackTrace()
        baseView.showServerError(message)
    }

    fun processApiError(response : Response<*>, baseView : BaseView) {
        val adapter = gson.getAdapter(ResponseWrapper::class.java)
        try {
            baseView.hideProgress()
            if (response.errorBody() != null) {
                val responseWrapper = adapter?.fromJson(response.errorBody()!!.string())
                if (responseWrapper != null) {
                    val code = responseWrapper.code
                    val message = responseWrapper.messages?.first()
                    when (code) {
                        RESPONSE_401 -> baseView.unauthorizedQuitApp()
                        RESPONSE_404 -> baseView.objectNotFound(message!!)
                        RESPONSE_500 -> baseView.showServerError("Something went wrong")
                        in RESPONSE_400..RESPONSE_500 -> {
                            if (!message.isNullOrEmpty())
                                baseView.showUserError(message)
                        }
                    }
                }
            }
        } catch (e : IOException) {
            e.printStackTrace()
        }

    }

}