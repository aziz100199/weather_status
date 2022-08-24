package com.example.weatherupdates.weather_data_base

import androidx.room.TypeConverter
import com.example.weatherupdates.weathermodel.Day
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class DayTypeConverter {

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
                object : TypeToken<List<String>>() {
                }.type
            )
            hDaysList
        } else {
            null
        }
    }

}
