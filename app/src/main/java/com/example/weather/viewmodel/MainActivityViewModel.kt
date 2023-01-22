package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.WeatherModel
import com.example.weather.services.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel:ViewModel() {
    val datas = MutableLiveData<WeatherModel>()
    val error = MutableLiveData<Boolean>()

    private val apiService  = APIService()
    private val disposable  = CompositeDisposable()

    fun refreshData(name :String) {
        getDataFromNet(name)
    }

    private fun getDataFromNet(name : String){
        disposable.add(
            apiService.getData(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<WeatherModel>(){
                    override fun onSuccess(t: WeatherModel) {
                        datas.value = t
                        error.value  = false
                    }

                    override fun onError(e: Throwable) {
                        error.value = true
                        e.printStackTrace()
                    }
                })
        )
    }

}