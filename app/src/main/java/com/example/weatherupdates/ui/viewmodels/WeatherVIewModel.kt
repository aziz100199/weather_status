package com.example.weatherupdates.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.weatherupdates.netwrok.WeatherApiInstance
import com.example.weatherupdates.roomdb.WeatherDatabase
import com.example.weatherupdates.roomdb.WeatherEntities
import com.example.weatherupdates.roomdb.WeatherRepository
import com.example.weatherupdates.weathermodel.Day
import kotlinx.coroutines.launch

class WeatherVIewModel(application: Application) : AndroidViewModel(application) {
    private val weatherMLD = MutableLiveData<List<Day>>()
    val weatherLD = liveData {
        emitSource(weatherMLD)
    }
    private val db = WeatherDatabase.getInstance(application)
    private val repository : WeatherRepository? = WeatherRepository(db)
    fun inIt() {
        weatherResponse()
    }

    private fun weatherResponse() {
        viewModelScope.launch {
            val response = WeatherApiInstance.WeatherApi.getWeather()
            if (response.isSuccessful && response.body() != null) {
                val isInsert = repository?.insert(
                    WeatherEntities(
                        conditions = response.body()!!.currentConditions?.conditions,
                        datetime = response.body()!!.currentConditions?.datetime,
                        datetimeEpoch = response.body()!!.currentConditions?.datetimeEpoch,
                        days = response.body()?.days!!
                    )
                )
            }
            weatherMLD.postValue(repository?.getAllItem()?.days)
        }
    }

    fun getResponse() {
        weatherMLD.postValue(repository?.getAllItem()?.days)
    }
}