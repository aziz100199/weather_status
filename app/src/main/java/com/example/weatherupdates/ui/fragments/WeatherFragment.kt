package com.example.weatherupdates.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherupdates.R
import com.example.weatherupdates.adapters.retrofit.WeatherAdapter
import com.example.weatherupdates.databinding.FragmentWeatherBinding
import com.example.weatherupdates.ui.viewmodels.WeatherVIewModel
import com.example.weatherupdates.utils.Utils


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
        recyclerView()
    }


    private fun recyclerView() {
        binding?.weatherRecycler?.apply {
            binding?.progressBar?.isVisible = true
            layoutManager = LinearLayoutManager(requireContext())
            weatherVIewModel.weatherLD.observe(requireActivity()) {
                if (it != null && it.isNotEmpty()) {
                    val weatherAdapter = WeatherAdapter(it) { position ->
                        Utils.currentPosition = position
                        goToNextFragment(R.id.weatherFragment_to_currentDayDetailFragment)
                    }
                    adapter = weatherAdapter
                    binding?.progressBar?.isVisible = false
                }

            }
        }

    }

    private fun goToNextFragment(id: Int) {
        findNavController().navigate(id)
    }
}