package com.example.weatherupdates.ui.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherupdates.R
import com.example.weatherupdates.databinding.FragmentCurrentDayDetailBinding
import com.example.weatherupdates.utils.Utils

class CurrentDayDetailFragment : Fragment() {
    private var binding: FragmentCurrentDayDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCurrentDayDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        binding?.apply {
            animationViewBG.backGroundAnimationView.setAnimation(R.raw.weather_detail_background)
            condition = Utils.weatherResponse[0].description
        }
    }

}