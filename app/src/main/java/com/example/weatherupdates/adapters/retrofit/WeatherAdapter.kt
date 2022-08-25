package com.example.weatherupdates.adapters.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherupdates.R
import com.example.weatherupdates.databinding.WeatherResponseLayoutBinding
import com.example.weatherupdates.utils.Utils.PARTLY_CLOUDY_DAY
import com.example.weatherupdates.utils.Utils.RAINY_DAY
import com.example.weatherupdates.utils.Utils.SUNNY_DAY
import com.example.weatherupdates.weathermodel.Day

class WeatherAdapter(private val list: List<Day?>?, val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<WeatherAdapter.VhRetrofit>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhRetrofit {
        val binding = WeatherResponseLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VhRetrofit(binding)
    }

    override fun onBindViewHolder(holder: VhRetrofit, position: Int) {
        holder.binding.apply {
            date = list!![position]?.datetime
            status = list[position]?.icon
            temperature = list[position]?.temp.toString()

            when (list[position]?.icon) {
                PARTLY_CLOUDY_DAY -> {
                    animation.animationView.setAnimation(R.raw.partly_cloudy_day)
                }
                RAINY_DAY -> {
                    animation.animationView.setAnimation(R.raw.rainy_day)
                }

                SUNNY_DAY -> {
                    animation.animationView.setAnimation(R.raw.sunny_day)
                }
            }
            weatherContainer.setOnClickListener {
                onClick(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class VhRetrofit(var binding: WeatherResponseLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}