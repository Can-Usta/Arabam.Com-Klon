package com.arabam.andrioid.assigment_main.model

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("id")
    val id : Int,
    @SerializedName("title")
    val title : String?,
    @SerializedName("location")
    val location : LocationResponse?,
    @SerializedName("modelName")
    val modelName : String?,
    @SerializedName("price")
    val price : Int?,
    @SerializedName("date")
    val date : String?,
    @SerializedName("photo")
    val photo : String?,
    @SerializedName("text")
    val text : String?
)
