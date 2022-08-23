package com.example.weatherupdates.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherupdates.R
import com.example.weatherupdates.databinding.ActivityCurrentDayDetailBinding
import com.example.weatherupdates.utils.Utils

class CurrentDayDetailActivity : AppCompatActivity() {
    private var binding: ActivityCurrentDayDetailBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrentDayDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navigateBack()
        setData()
    }

    private fun setData() {
        binding?.apply {
            animationViewBG.backGroundAnimationView.setAnimation(R.raw.weather_detail_background)
            description = Utils.weatherResponse[0].description
        }
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