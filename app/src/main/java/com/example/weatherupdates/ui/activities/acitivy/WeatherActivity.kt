package com.example.weatherupdates.ui.activities.acitivy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherupdates.databinding.ActivityWeatherBinding
import com.example.weatherupdates.ui.activities.viewmodels.WeatherVIewModel

class WeatherActivity : AppCompatActivity() {
    private var binding: ActivityWeatherBinding? = null
    private val weatherVIewModel by viewModels<WeatherVIewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navigateBack()
        weatherVIewModel.inIt()
    }

    private fun navigateBack() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}