package com.example.weatherupdates.dayaconverter

import androidx.room.TypeConverter
import com.example.weatherupdates.weathermodel.Day
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class WeatherTypeConverter {


    @TypeConverter
    fun hConvertDaysListToString(hDaysList: List<Day>?): String? {
        hDaysList?.let {
            val hGson = GsonBuilder()
                .setPrettyPrinting()
                .create()

            return hGson.toJson(it)
        }
        return null
    }


    @TypeConverter
    fun hConvertStringToDaysList(listString: String?): List<Day>? {
        val hGson = GsonBuilder().setPrettyPrinting().create()

        return if (listString != null && listString != "null") {
            val hDaysList: List<Day> = hGson.fromJson(
                listString,
                object : TypeToken<List<Day>>() {
                }.type
            )
            hDaysList
        } else {
            null
        }
    }


}