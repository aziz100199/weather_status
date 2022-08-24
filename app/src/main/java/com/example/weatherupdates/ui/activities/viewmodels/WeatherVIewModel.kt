package com.example.weatherupdates.ui.activities.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.weatherupdates.netwrok.WeatherApiInstance
import com.example.weatherupdates.weather_data_base.WeatherDatabase
import com.example.weatherupdates.weather_data_base.WeatherEntities
import com.example.weatherupdates.weather_data_base.WeatherRepository
import com.example.weatherupdates.weathermodel.WheatherResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class WeatherVIewModel(application: Application) : AndroidViewModel(application) {
    private val weatherMLD = MutableLiveData<WheatherResponse>()
    val weatherLD = liveData {
        emitSource(weatherMLD)
    }
    private val db = WeatherDatabase.getInstance(application)
    private val repository = WeatherRepository(db)
    fun inIt() {
        weatherResponse()
    }

    private fun weatherResponse() {
        viewModelScope.launch {
            val response = WeatherApiInstance.WeatherApi.getWeather()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                weatherMLD.postValue(body!!)
                Timber.d(response.isSuccessful.toString())

                val weatherEntities = WeatherEntities(
                    currentConditions = body.currentConditions,
                    days = body.days,
                    description = body.description,
                    latitude = body.latitude,
                    longitude = body.longitude,
                    queryCost = body.queryCost,
                    resolvedAddress = body.resolvedAddress,
                    stations = body.stations,
                    timezone = body.timezone,
                    tzoffset = body.tzoffset,
                )

                repository.insert(weatherEntities)
                Timber.d("Inserstaion complete")
            }
        }
    }


}