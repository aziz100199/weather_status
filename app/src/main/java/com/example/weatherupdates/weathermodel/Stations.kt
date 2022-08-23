package com.example.weatherupdates.weathermodel


import com.google.gson.annotations.SerializedName

data class Stations(
    @SerializedName("AV559")
    val aV559: AV559?,
    @SerializedName("VIDP")
    val vIDP: VIDP?
)