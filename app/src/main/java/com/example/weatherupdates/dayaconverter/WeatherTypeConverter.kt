package com.example.weatherupdates.dayaconverter

import androidx.room.TypeConverter
import com.example.weatherupdates.weathermodel.Day
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class WeatherTypeConverter {


    @TypeConverter
    fun convertDaysListToString(daysList: List<Day>?): String? {
       daysList?.let {
            val hGson = GsonBuilder()
                .setPrettyPrinting()
                .create()

            return hGson.toJson(it)
        }
        return null
    }


    @TypeConverter
    fun convertStringToDaysList(listString: String?): List<Day>? {
        val gson = GsonBuilder().setPrettyPrinting().create()

        return if (listString != null && listString != "null") {
            val daysList: List<Day> = gson.fromJson(
                listString,
                object : TypeToken<List<Day>>() {
                }.type
            )
            daysList
        } else {
            null
        }
    }


}