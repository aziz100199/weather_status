package com.example.weatherupdates.weather_data_base

import androidx.room.TypeConverter
import com.example.weatherupdates.weathermodel.CurrentConditions
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class CurrentConditionsTypeConverter {

    @TypeConverter
    fun hConvertCurrentConditionsString(hCurrentConditions: CurrentConditions?): String? {
        hCurrentConditions?.let {
            val hGson = GsonBuilder()
                .setPrettyPrinting()
                .create()

            return hGson.toJson(it)
        }
        return null
    }


    @TypeConverter
    fun hConvertStringToCurrentConditions(currentConditionsString: String?): CurrentConditions? {
        val hGson = GsonBuilder().setPrettyPrinting().create()

        return if (currentConditionsString != null && currentConditionsString != "null") {
            val hCurrentConditions: CurrentConditions = hGson.fromJson(
                currentConditionsString,
                object : TypeToken<CurrentConditions>() {
                }.type
            )
            hCurrentConditions
        } else {
            null
        }
    }

}
