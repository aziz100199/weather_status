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
            when (Utils.currentPosition) {
                0 -> {
                    setAnimation(0)
                }
                1 -> {
                    setAnimation(1)
                }
                2 -> {
                    setAnimation(2)
                }
                3 -> {
                    setAnimation(3)
                }
                4 -> {
                    setAnimation(4)
                }
                5 -> {
                    setAnimation(5)
                }
                6 -> {
                    setAnimation(6)
                }
                7 -> {
                    setAnimation(7)
                }
                8 -> {
                    setAnimation(8)
                }
                9 -> {
                    setAnimation(9)
                }
                10 -> {
                    setAnimation(10)
                }
                11 -> {
                    setAnimation(11)
                }
                12 -> {
                    setAnimation(12)
                }
                13 -> {
                    setAnimation(13)
                }
                14 -> {
                    setAnimation(14)
                }
                else -> {
                    setAnimation(0)
                }
            }
        }
    }

    private fun setAnimation(position: Int) {
        binding?.apply {
            Utils.weatherResponse[0].apply {
                date = days!![position]?.datetime.toString()
                condition = days[position]?.conditions
                wind = days[position]?.winddir.toString()
                hum = days[position]?.humidity.toString()
                pressure = days[position]?.pressure.toString()
                temperature = days[position]?.temp.toString()
                windspeed = days[position]?.windspeed.toString()
                information = days[position]?.description.toString()

                when (Utils.weatherResponse[0].days?.get(position)?.icon.toString()) {
                    Utils.PARTLY_CLOUDY_DAY -> {
                        weatherConIcon.animationView.setAnimation(R.raw.partly_cloudy_day)
                    }
                    Utils.RAINY_DAY -> {
                        weatherConIcon.animationView.setAnimation(R.raw.rainy_day)
                        animationViewBG.backGroundAnimationView.setAnimation(R.raw.cloudy)
                    }

                    Utils.SUNNY_DAY -> {
                        weatherConIcon.animationView.setAnimation(R.raw.sunny_day)

                    }
                }
            }
        }

    }
}