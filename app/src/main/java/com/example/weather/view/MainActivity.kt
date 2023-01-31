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



class MainActivity : AppCompatActivity() {

    private val viewModel : MainActivityViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.searchButton.setOnClickListener {
            val cityName = binding.cityName.text.toString()
            viewModel.getWeatherByCity(cityName.toLowerCase())
        }

        observeLiveData()
    }

    private fun observeWeather() {
        viewModel.weather.observe(this, Observer{ indicator ->
            indicator?.let {
                binding.textLayout.visible()
                binding.imageLayout.visible()
                binding.errorText.gone()
                binding.cityText.text = indicator.name.toString()

                binding.image.loadImage("https://openweathermap.org/img/wn/" + indicator.weather[0].icon + "@2x.png")
                binding.degree.text = indicator.main.temp.toString() + "°C"

            }

        })
    }

    private fun observeError() {
        viewModel.error.observe(this, Observer { indicator->
            indicator?.let{
                if(indicator){
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