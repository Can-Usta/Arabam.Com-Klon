package com.arabam.andrioid.assigment_main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arabam.andrioid.assigment_main.model.Car
import com.arabam.andrioid.assigment_main.service.CarApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CarListViewModel : ViewModel(){
    val cars = MutableLiveData<List<Car>>()
    val carLoad = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<Boolean>()
    val carApiService = CarApiService()

    private val disposable = CompositeDisposable()


    fun refreshCarList(take: Int,skip: Int){
        loadFromApi(take,skip)
    }

    private fun loadFromApi(take : Int, skip : Int) {
        disposable.add(
            carApiService.getCars(take,skip)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Car>>(){
                    override fun onSuccess(t: List<Car>) {
                        cars.value = t
                        errorMessage.value = false
                        carLoad.value = false

                    }

                    override fun onError(e: Throwable) {
                        errorMessage.value = true
                        carLoad.value = false
                        println(e)
                    }
                })
        )
    }

}