package com.arabam.andrioid.assigment_main.model

import com.google.gson.annotations.SerializedName

data class PropertiesResponse(
    @SerializedName("name")
    val propertiesName : String?,
    @SerializedName("value")
    val propertiesValue: String?
)
