package com.uygulamalarim.weatherapp_v2

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.uygulamalarim.weatherapp_v2.WeatherService.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainViewModel
    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        progressBar.visibility= View.VISIBLE
        layout.visibility=View.GONE

        GET=getSharedPreferences(packageName, MODE_PRIVATE)
        SET=GET.edit()
        viewModel= ViewModelProvider(this).get(MainViewModel::class.java)
        var cName=GET.getString("cityName","istanbul")
        viewModel.refreshData(cName!!)
        getLiveData()











    }


    private fun getLiveData() {
        viewModel.weather_data.observe(this) { data ->
            data?.let {
                heat.text = data.main.temp.toString()
                description.text=data.weather.get(0).description.uppercase()
                toolbar.setTitle(data.name.toString())
                toolbar.setSubtitle(data.sys.country.toString())
                humidity.text = data.main.humidity.toString()+" %"
                wind_speed.text = data.wind.speed.toString()+" Km/h"
                Glide.with(this)
                    .load("http://openweathermap.org/img/wn/" + data.weather.get(0).icon + "@2x.png")
                    .into(weatherPic)
                layout.visibility=View.VISIBLE
                progressBar.visibility=View.GONE


                searchview.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchview.clearFocus()
                        if (data.cod.toString()=="200"){
                            viewModel.refreshData(query!!)
                            getLiveData()
                            searchview.setIconified(true)


                        }else{
                            Toast.makeText(applicationContext, "You have entered invalid city name please try again", Toast.LENGTH_SHORT).show()
                        }
                    return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })



            }


        }





    }




}