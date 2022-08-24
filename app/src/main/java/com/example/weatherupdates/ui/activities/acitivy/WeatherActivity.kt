package com.example.weatherupdates.ui.activities.acitivy

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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
        navigateBack()
        connectionDetector()
    }

    private fun connectionDetector() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                lifecycleScope.launch {
                    weatherVIewModel.inIt()
                    observeValue()
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

    private fun observeValue() {
        weatherVIewModel.weatherLD.observe(this) { observeValue ->
            Utils.weatherResponse.addAll(listOf(observeValue))
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