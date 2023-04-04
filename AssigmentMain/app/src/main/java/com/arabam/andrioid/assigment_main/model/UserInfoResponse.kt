package com.arabam.andrioid.assigment_main.model

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("id")
    val userInfoId : Int?,
    @SerializedName("nameSurname")
    val userInfoNameSurname : String?,
    @SerializedName("phone")
    val userInfoPhone : String?,
    @SerializedName("phoneFormatted")
    val userInfoPhoneFormatted : String?
)
