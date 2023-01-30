package com.example.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.extensions.gone
import com.example.weather.extensions.loadImage
import com.example.weather.extensions.visible
import com.example.weather.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel : MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Todo: Xml'de default olarak asagidaki componentlerin visibility'leri gone yapilirsa asagidaki koda ihtiyac kalmayacak.
        /*textLayout.visibility = View.GONE
        imageLayout.visibility = View.GONE
        errorText.visibility = View.GONE*/

        searchButton.setOnClickListener {
            val cityName = city_name.text.toString()
            viewModel.getWeatherByCity(cityName.toLowerCase())
            //val hold : MainActivityViewModel by viewModels() // Todo: ViewModel global olarak tanimlanmali.
            //this.vm = hold // Todo: Global olarak viewModel degiskeni tanimlandiginda hold'a ihtiyac kalmayacak.
            //hold.refreshData(city_name.toLowerCase()) // Todo: refreshData ismi degistirilmeli.
            //getLiveData() // Todo: Bu methodun ismi degistirilmeli ve onCreate içine alınmalı.
        }

        observeLiveData()
    }

    private fun observeWeather() {
        viewModel.weather.observe(this, Observer{ indicator ->
            indicator?.let {
                // Todo: asagidakilar icin extension yazilabilir.
                textLayout.visible()
                imageLayout.visible()
                errorText.gone()
                cityText.text = indicator.name.toString()

                // Todo: Glide icin imageview'e extension yazilabilir.

                image.loadImage("https://openweathermap.org/img/wn/" + indicator.weather[0].icon + "@2x.png")
                /*Glide.with(this)
                    .load("https://openweathermap.org/img/wn/" + indicator.weather[0].icon + "@2x.png")
                    .into(image)
                 */
                degree.text = indicator.main.temp.toString() + "°C"

            }

        })
    }

    private fun observeError() {
        viewModel.error.observe(this, Observer { indicator->
            indicator?.let{
                if(indicator){
                    // Todo: asagidakilar icin extension yazilabilir.
                    errorText.visible()
                    textLayout.gone()
                    imageLayout.gone()

                }else {
                    errorText.gone()

                }
            }
        })
    }


    private fun observeLiveData(){
        observeWeather()
        observeError()
    }


}