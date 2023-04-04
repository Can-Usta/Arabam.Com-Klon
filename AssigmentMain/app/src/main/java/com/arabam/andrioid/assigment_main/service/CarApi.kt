package com.arabam.andrioid.assigment_main.service

import com.arabam.andrioid.assigment_main.model.Car
import com.arabam.andrioid.assigment_main.model.CarDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {
    @GET("api/v1/listing?sort=1&sortDirection=0")
    fun getCarList( @Query("take") take : Int, @Query("skip") skip : Int) : Single<List<Car>>

    @GET("api/v1/detail")
    fun getCarDetails(@Query("id") id: Int ) : Single<CarDetailResponse>
}