package com.example.weatherupdates.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherupdates.weathermodel.Day

@Entity
data class WeatherEntities(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 1,
    val conditions: String?,
    val datetime: String?,
    val datetimeEpoch: Int?,
    val days: List<Day> = listOf(),


)