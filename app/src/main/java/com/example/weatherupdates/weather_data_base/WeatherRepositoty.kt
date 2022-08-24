package com.example.weatherupdates.weather_data_base

import com.example.weatherupdates.weathermodel.WheatherResponse

class WeatherRepository(private var db: WeatherDatabase) {
    fun insert(item: WeatherEntities) = db.weatherDao().inset(item)
    fun delete(item: WeatherEntities) = db.weatherDao().deletData(item)
    fun update(item: WeatherEntities) = db.weatherDao().upDate(item)
    fun getAllItem() = db.weatherDao().getdata()

}