package com.example.weatherupdates.weathermodel


import com.google.gson.annotations.SerializedName

data class VIDP(
    @SerializedName("contribution")
    val contribution: Double?,
    @SerializedName("distance")
    val distance: Double?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("quality")
    val quality: Int?,
    @SerializedName("useCount")
    val useCount: Int?
)