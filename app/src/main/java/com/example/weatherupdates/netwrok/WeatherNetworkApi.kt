package com.example.weatherupdates.netwrok

import com.example.weatherupdates.utils.EndPoints.WEATHER_END_POINT
import com.example.weatherupdates.weathermodel.WheatherResponse
import retrofit2.Response
import retrofit2.http.GET

interface WeatherNetworkApi {
    @GET(WEATHER_END_POINT)
    suspend fun getWeather(): Response<WheatherResponse>
}