package com.arabam.andrioid.assigment_main.util

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.andrioid.assigment_main.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import android.widget.ImageView


fun ImageView.downloadImageForApi( url : String?, placeholder: CircularProgressDrawable){
    val replacedUrl = url?.replace("{0}","800x600")
    val options =RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher)
    Glide.with(context).setDefaultRequestOptions(options).load(replacedUrl).into(this)

}

fun createPlaceholder(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 7f
        centerRadius = 40f
        start()
    }
}