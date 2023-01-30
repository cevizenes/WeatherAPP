package com.example.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.extensions.gone
import com.example.weather.extensions.loadImage
import com.example.weather.extensions.visible
import com.example.weather.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val viewModel : MainActivityViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Todo: Xml'de default olarak asagidaki componentlerin visibility'leri gone yapilirsa asagidaki koda ihtiyac kalmayacak.
        /*textLayout.visibility = View.GONE
        imageLayout.visibility = View.GONE
        errorText.visibility = View.GONE*/

        searchButton.setOnClickListener {
            val cityName = binding.cityName.text.toString()
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
                binding.textLayout.visible()
                binding.imageLayout.visible()
                binding.errorText.gone()
                binding.cityText.text = indicator.name.toString()

                // Todo: Glide icin imageview'e extension yazilabilir.

               binding.image.loadImage("https://openweathermap.org/img/wn/" + indicator.weather[0].icon + "@2x.png")
                /*Glide.with(this)
                    .load("https://openweathermap.org/img/wn/" + indicator.weather[0].icon + "@2x.png")
                    .into(image)
                 */
                binding.degree.text = indicator.main.temp.toString() + "°C"

            }

        })
    }

    private fun observeError() {
        viewModel.error.observe(this, Observer { indicator->
            indicator?.let{
                if(indicator){
                    // Todo: asagidakilar icin extension yazilabilir.
                    binding.errorText.visible()
                    binding.textLayout.gone()
                    binding.imageLayout.gone()

                }else {
                    binding.errorText.gone()

                }
            }
        })
    }


    private fun observeLiveData(){
        observeWeather()
        observeError()
    }


}