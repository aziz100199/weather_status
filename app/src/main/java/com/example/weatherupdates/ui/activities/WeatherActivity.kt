package com.example.weatherupdates.ui.activities

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
import com.example.weatherupdates.ui.viewmodels.WeatherVIewModel
import com.example.weatherupdates.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


class WeatherActivity : AppCompatActivity() {
    private var binding: ActivityWeatherBinding? = null
    private val weatherVIewModel by viewModels<WeatherVIewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        navigateBack()
        connectionDetector()
        weatherVIewModel.getResponse()
        observeValue()
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
                lifecycleScope.launch(Dispatchers.IO) {
                    while (true) {

                        withContext(Dispatchers.Main){
                            weatherVIewModel.inIt()
                            observeValue()
                        }

                        delay(700000)
                    }
                }
                Timber.d("net work available")
            }

            // Network capabilities have changed for the network
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                Timber.d("network onCapabilitiesChanged")
                super.onCapabilitiesChanged(network, networkCapabilities)
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
            }

            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)
                Timber.d("network onLost")
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
            Utils.weatherResponse = observeValue
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