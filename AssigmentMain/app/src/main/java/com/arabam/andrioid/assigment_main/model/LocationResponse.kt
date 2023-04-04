package com.arabam.andrioid.assigment_main.model

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("cityName")
    val cityName : String?,
    @SerializedName("townName")
    val townName : String?
)
