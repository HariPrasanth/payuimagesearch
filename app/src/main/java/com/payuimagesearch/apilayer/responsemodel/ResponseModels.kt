package com.payuimagesearch.apilayer.responsemodel

import java.io.Serializable

class ImagesResponse(
    var total: Int,
    var totalHits: Int,
    var hits: ArrayList<PixImage>
) : Serializable

class PixImage(var id: Int, var imageURL: String, var previewURL: String, var largeImageURL: String, var webformatURL: String) : Serializable
