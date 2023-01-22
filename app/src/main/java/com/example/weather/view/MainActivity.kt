package com.example.weather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var vm :MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textLayout.visibility = View.GONE
        imageLayout.visibility = View.GONE
        errorText.visibility = View.GONE

        searchButton.setOnClickListener {
            val city_name = city_name.text.toString()
            val hold : MainActivityViewModel by viewModels()
            this.vm = hold
            hold.refreshData(city_name.toLowerCase())
            getLiveData()
        }

    }
    private fun getLiveData(){
        vm.datas.observe(this, Observer{indicator ->
            indicator?.let {
                textLayout.visibility = View.VISIBLE
                imageLayout.visibility = View.VISIBLE
                errorText.visibility = View.GONE
                cityText.text = indicator.name.toString()

               Glide.with(this)
                   .load("https://openweathermap.org/img/wn/" + indicator.weather[0].icon + "@2x.png")
                   .into(image)

                degree.text = indicator.main.temp.toString() + "°C"

            }

        })
        vm.error.observe(this, Observer { indicator->
            indicator?.let{
                if(indicator){
                    errorText.visibility = View.VISIBLE
                    textLayout.visibility = View.GONE
                    imageLayout.visibility = View.GONE

                }else {
                    errorText.visibility = View.GONE

                }
            }
        })
    }


}