package com.arabam.andrioid.assigment_main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arabam.andrioid.assigment_main.model.CarDetailResponse
import com.arabam.andrioid.assigment_main.service.CarApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CarDetailViewModel : ViewModel() {
    val carLiveData = MutableLiveData<CarDetailResponse>()
    val detailErrorMessage = MutableLiveData<Boolean>()
    private val carApiService = CarApiService()


    private val disposable = CompositeDisposable()

    fun getDetail(id : Int){
        disposable.add(
            carApiService.getCarDetails(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CarDetailResponse>(){
                    override fun onSuccess(t: CarDetailResponse) {
                        carLiveData.value = t
                        detailErrorMessage.value = false
                    }

                    override fun onError(e: Throwable) {
                        detailErrorMessage.value=true
                        println(e)
                    }

                })
        )
    }
}