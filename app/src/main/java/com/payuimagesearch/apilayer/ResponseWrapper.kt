package com.payuimagesearch.apilayer

import com.google.gson.annotations.SerializedName

class ResponseWrapper<T>{

    @SerializedName("status")
    var status : String? = null

    @SerializedName("code")
    var code : Int? = null

    @SerializedName("result")
    var result : T? = null

    @SerializedName("messages")
    var messages : Array<String>? = null
}