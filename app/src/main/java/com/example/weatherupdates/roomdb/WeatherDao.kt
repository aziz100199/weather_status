package com.example.weatherupdates.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDataAccessOpject {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inset(name: WeatherEntities)

    @Query("SELECT * FROM WeatherEntities")
    fun getData(): WeatherEntities

}