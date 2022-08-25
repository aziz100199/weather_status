package com.example.weatherupdates.netwrok

import com.example.weatherupdates.utils.EndPoints.WEATHER_END_POINT
import com.example.weatherupdates.utils.Utils
import com.example.weatherupdates.weathermodel.WheatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherNetworkApi {
    @GET(WEATHER_END_POINT)
    suspend fun getWeather(
        @Path("place") path: String = "islambad",
        @Query("unitGroup") metric: String = "metric",
        @Query("key") key: String = Utils.KEY,
        @Query("contentType") json: String = "json"
    ): Response<WheatherResponse>
}