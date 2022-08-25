package com.example.weatherupdates.weather_data_base

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherupdates.weathermodel.Day

@Entity
data class WeatherEntities(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val conditions: String?,
    val datetime: String?,
    val datetimeEpoch: Int?,
    val days: List<Day> = listOf(),
    /* @ColumnInfo(name = "description")
     val description: String?,
     @ColumnInfo(name = "dew")
     val dew: Double?,
     @ColumnInfo(name = "feelslike")
     val feelslike: Double?,
     @ColumnInfo(name = "feelslikemax")
     val feelslikemax: Double?,
     @ColumnInfo(name = "feelslikemin")
     val feelslikemin: Double?,
     @ColumnInfo(name = "humidity")
     val humidity: Double?,
     @ColumnInfo(name = "icon")
     val icon: String?,
     @ColumnInfo(name = "moonphase")
     val moonphase: Double?,
     @ColumnInfo(name = "precip")
     val precip: Double?,
     @ColumnInfo(name = "precipcover")
     val precipcover: Double?,
     @ColumnInfo(name = "precipprob")
     val precipprob: Double?,
     @ColumnInfo(name = "pressure")
     val pressure: Double?,
     @ColumnInfo(name = "severerisk")
     val severerisk: Double?,
     @ColumnInfo(name = "snow")
     val snow: Double?,
     @ColumnInfo(name = "snowdepth")
     val snowdepth: Double?,
     @ColumnInfo(name = "solarenergy")
     val solarenergy: Double?,
     @ColumnInfo(name = "solarradiation")
     val solarradiation: Double?,
     @ColumnInfo(name = "source")
     val source: String?,
     @ColumnInfo(name = "sunrise")
     val sunrise: String?,
     @ColumnInfo(name = "sunriseEpoch")
     val sunriseEpoch: Int?,
     @ColumnInfo(name = "sunset")
     val sunset: String?,
     @ColumnInfo(name = "sunsetEpoch")
     val sunsetEpoch: Int?,
     @ColumnInfo(name = "temp")
     val temp: Double?,
     @ColumnInfo(name = "tempmax")
     val tempmax: Double?,
     @ColumnInfo(name = "tempmin")
     val tempmin: Double?,
     @ColumnInfo(name = "uvindex")
     val uvindex: Double?,
     @ColumnInfo(name = "visibility")
     val visibility: Double?,
     @ColumnInfo(name = "winddir")
     val winddir: Double?,
     @ColumnInfo(name = "windgust")
     val windgust: Double?,
     @ColumnInfo(name = "windspeed")
     val windspeed: Double?
 */

)