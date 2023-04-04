package com.arabam.andrioid.assigment_main.model

import com.google.gson.annotations.SerializedName

data class CarDetailResponse(
    @SerializedName("id")
    var id : Int,
    @SerializedName("title")
    var title : String?,
    @SerializedName("location")
    var location : LocationResponse,
    @SerializedName("category")
    var category : CategoryResponse,
    @SerializedName("modelName")
    var modelName : String?,
    @SerializedName("price")
    var price : Int?,
    @SerializedName("date")
    var date : String?,
    @SerializedName("photos")
    var photos : List<String>?,
    @SerializedName("properties")
    var properties : List<PropertiesResponse>?,
    @SerializedName("text")
    var text : String?,
    @SerializedName("userInfo")
    var userInfo : UserInfoResponse

)
