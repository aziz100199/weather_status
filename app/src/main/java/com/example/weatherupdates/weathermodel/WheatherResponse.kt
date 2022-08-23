package com.example.weatherupdates.weathermodel


import com.google.gson.annotations.SerializedName

data class WheatherResponse(
    @SerializedName("address")
    val address: String?,
    @SerializedName("alerts")
    val alerts: List<Any?>?,
    @SerializedName("currentConditions")
    val currentConditions: CurrentConditions?,
    @SerializedName("days")
    val days: List<Day?>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("queryCost")
    val queryCost: Int?,
    @SerializedName("resolvedAddress")
    val resolvedAddress: String?,
    @SerializedName("stations")
    val stations: Stations?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("tzoffset")
    val tzoffset: Double?
)