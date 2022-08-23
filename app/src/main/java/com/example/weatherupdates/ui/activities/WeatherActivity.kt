package com.example.weatherupdates.ui.activities

import android.app.Activity
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherupdates.adapters.retrofit.WeatherAdapter
import com.example.weatherupdates.databinding.ActivityWeatherBinding
import com.example.weatherupdates.ui.activities.viewmodels.WeatherVIewModel
import com.example.weatherupdates.utils.Utils
import kotlinx.coroutines.launch

class WeatherActivity : AppCompatActivity() {
    private var binding: ActivityWeatherBinding? = null
    private val weatherVIewModel by viewModels<WeatherVIewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        connectionDetector()
        navigateBack()
    }

    private fun navigateBack() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }


    private fun connectionDetector() {
        binding?.progressBar?.isVisible = true
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                weatherVIewModel.inIt()
                lifecycleScope.launch {
                    recyclerView()
                }

            }

            // Network capabilities have changed for the network
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)

            }

            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)

            }
        }
        val connectivityManager =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getSystemService(ConnectivityManager::class.java) as ConnectivityManager
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    private fun recyclerView() {

        binding?.weatherRecycler?.apply {
            binding?.progressBar?.isVisible = true
            layoutManager = LinearLayoutManager(this@WeatherActivity)
            weatherVIewModel.weatherLD.observe(this@WeatherActivity) { observeValue ->
                Utils.weatherResponse.addAll(listOf(observeValue))
                val weatherAdapter = WeatherAdapter(observeValue.days) { position ->
                    when (position) {
                        0 -> activityLaunch(CurrentDayDetailActivity())
                    }
                }
                adapter = weatherAdapter
                binding?.progressBar?.isVisible = false
            }
        }

    }

    private fun activityLaunch(activity: Activity) {
        startActivity(Intent(this, activity::class.java))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}