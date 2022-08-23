package com.example.weatherupdates.adapters.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherupdates.R
import com.example.weatherupdates.databinding.WeatherResponseLayoutBinding
import com.example.weatherupdates.weathermodel.Day
import timber.log.Timber

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
            status = list[position]?.conditions
            temperature = list[position]?.temp.toString()
            Timber.d(list[position]?.icon)
            when (list[position]?.icon) {
                "partly-cloudy-day" -> tempIcon.setImageResource(R.drawable.ic_partialy_cloudy)
                "rain" -> tempIcon.setImageResource(R.drawable.ic_rain)
                "clear-day" -> tempIcon.setImageResource(R.drawable.ic_suny)
            }
            weatherContainer.setOnClickListener{
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