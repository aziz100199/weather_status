package com.example.weatherupdates.ui.activities.fragments

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherupdates.R
import com.example.weatherupdates.adapters.retrofit.WeatherAdapter
import com.example.weatherupdates.databinding.FragmentWeatherBinding
import com.example.weatherupdates.ui.activities.viewmodels.WeatherVIewModel
import com.example.weatherupdates.utils.Utils
import kotlinx.coroutines.launch


class WeatherFragment : Fragment() {
    private var binding: FragmentWeatherBinding? = null
    private val weatherVIewModel by activityViewModels<WeatherVIewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectionDetector()
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
                requireActivity().getSystemService(ConnectivityManager::class.java) as ConnectivityManager
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    private fun recyclerView() {

        binding?.weatherRecycler?.apply {
            binding?.progressBar?.isVisible = true
            layoutManager = LinearLayoutManager(requireContext())
            weatherVIewModel.weatherLD.observe(requireActivity()) { observeValue ->
                Utils.weatherResponse.addAll(listOf(observeValue))
                val weatherAdapter = WeatherAdapter(observeValue.days) { position ->
                    when (position) {
                        0 -> goToNextFragment()
                    }
                }
                adapter = weatherAdapter
                binding?.progressBar?.isVisible = false
            }
        }

    }

    private fun goToNextFragment() {
        findNavController().navigate(R.id.weatherFragment_to_currentDayDetailFragment)
    }

}