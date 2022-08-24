package com.example.weatherupdates.weather_data_base

import androidx.room.TypeConverter
import com.example.weatherupdates.weathermodel.Day
import com.example.weatherupdates.weathermodel.Stations
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class StationsTypeConverter {

    @TypeConverter
    fun hConvertStationsToString(hStations: Stations?): String? {
        hStations?.let {
            val hGson = GsonBuilder()
                .setPrettyPrinting()
                .create()

            return hGson.toJson(it)
        }
        return null
    }


    @TypeConverter
    fun hConvertStringToStations(stations: String?): Stations? {
        val hGson = GsonBuilder().setPrettyPrinting().create()

        return if (stations != null && stations != "null") {
            val hDay: Stations = hGson.fromJson(
                stations,
                object : TypeToken<Stations>() {
                }.type
            )
            hDay
        } else {
            null
        }
    }

}
