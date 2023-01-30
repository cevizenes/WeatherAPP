package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.WeatherResponse
import com.example.weather.services.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel:ViewModel() {
    // Todo: Datas ne datasi iceriyor? isim gozden gecirilmeli.
    val weather = MutableLiveData<WeatherResponse>()
    val error = MutableLiveData<Boolean>()

    private val apiService  = APIService()
    private val disposable  = CompositeDisposable()

    fun getWeatherByCity(name :String) {
    disposable.add(
            apiService.getWeatherByCity(name)
                .subscribeOn(Schedulers.io()) // IO thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<WeatherResponse>(){
                    override fun onSuccess(t: WeatherResponse) {
                        weather.value = t
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