package com.arabam.andrioid.assigment_main.service

import com.arabam.andrioid.assigment_main.model.Car
import com.arabam.andrioid.assigment_main.model.CarDetailResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CarApiService {
    private val BASE_URL = "https://sandbox.arabamd.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CarApi::class.java)

    fun getCars(take : Int,skip : Int) : Single<List<Car>> {
        return api.getCarList(take,skip)
    }
    fun getCarDetails(id : Int) : Single<CarDetailResponse>{
        return api.getCarDetails(id)
    }
   /* "?sort=1&sortDirection=0&take=20&photoSize=120x90"*/
}