package com.example.weatherupdates.netwrok

import com.example.weatherupdates.utils.EndPoints
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiInstance {

    private fun loggingClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    //// it used for checking null object in response
    private val gson: Gson = GsonBuilder().serializeNulls().create()
    val WeatherApi: WeatherNetworkApi by lazy {
        Retrofit.Builder().baseUrl(EndPoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(loggingClient())
            .build().create(WeatherNetworkApi::class.java)
    }

}