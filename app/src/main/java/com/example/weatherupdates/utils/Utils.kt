package com.example.weatherupdates.utils

import com.example.weatherupdates.weathermodel.Day

object Utils {
    var currentPosition: Int? = null
    const val PARTLY_CLOUDY_DAY = "partly-cloudy-day"
    const val RAINY_DAY = "rain"
    const val SUNNY_DAY = "clear-day"
    var weatherResponse : List<Day>? = listOf<Day>()
    const val KEY = "YHXYMQ8RBA9W4GES6R5MWJAUF"
}
