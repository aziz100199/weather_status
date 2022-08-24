package com.example.weatherupdates.weather_data_base

import androidx.room.*
import com.example.weatherupdates.weathermodel.WheatherResponse

@Dao
interface UserDataAccessOpject {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inset(name: WeatherEntities)

    @Query("SELECT * FROM WeatherEntities")
    fun getdata(): List<WeatherEntities>

    @Delete
    fun deletData(item: WeatherEntities)
    @Update
    fun upDate(item: WeatherEntities)
}