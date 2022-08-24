package com.example.weatherupdates.weather_data_base

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherupdates.weathermodel.CurrentConditions
import com.example.weatherupdates.weathermodel.Day
import com.example.weatherupdates.weathermodel.Stations
import com.example.weatherupdates.weathermodel.WheatherResponse

@Entity
data class WeatherEntities(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @TypeConverters(CurrentConditionsTypeConverter::class)
    @ColumnInfo(name = "currentConditions")
     val currentConditions: CurrentConditions?,


    @TypeConverters(DayTypeConverter::class)
    @ColumnInfo(name = "days")
     val days: List<Day?>?,

    @ColumnInfo(name = "description")
     val description: String?,
    @ColumnInfo(name = "latitude")
     val latitude: Double?,
    @ColumnInfo(name = "longitude")
     val longitude: Double?,
    @ColumnInfo(name = "queryCost")
     val queryCost: Int?,
    @ColumnInfo(name = "resolvedAddress")
     val resolvedAddress: String?,

    @TypeConverters(StationsTypeConverter::class)
    @ColumnInfo(name = "stations")
     val stations: Stations?,

    @ColumnInfo(name = "timezone")
     val timezone: String?,
    @ColumnInfo(name = "tzoffset")
     val tzoffset: Double?

)