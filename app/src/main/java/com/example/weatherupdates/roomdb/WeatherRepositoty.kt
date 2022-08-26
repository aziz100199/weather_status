package com.example.weatherupdates.roomdb

class WeatherRepository(private var db: WeatherDatabase) {
    fun insert(item: WeatherEntities) = db.weatherDao().inset(item)
    fun getAllItem() = db.weatherDao().getData()

}