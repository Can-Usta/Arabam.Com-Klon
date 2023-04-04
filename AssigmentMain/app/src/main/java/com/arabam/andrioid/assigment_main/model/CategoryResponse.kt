package com.arabam.andrioid.assigment_main.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("id")
    val categoryId : Int?,
    @SerializedName("name")
    val categoryName :  String?
)
