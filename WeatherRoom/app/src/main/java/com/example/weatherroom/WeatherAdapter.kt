package com.example.weatherroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.lang.IllegalArgumentException

private const val TYPE_COLD = 0
private const val TYPE_WARM = 1

class WeatherAdapter: ListAdapter<Weather, RecyclerView.ViewHolder>(WeatherDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_COLD -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_cold_holder, parent, false)
                WeatherColdHolder(view)
            }
            TYPE_WARM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_warm_holder, parent, false)
                WeatherWarmHolder(view)
            }
            else ->{
                throw IllegalArgumentException("Missing type of holder")
            }
        }
        //
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_COLD -> (holder as WeatherColdHolder).bind(getItem(position))
            TYPE_WARM -> (holder as WeatherWarmHolder).bind(getItem(position))
            else -> throw IllegalArgumentException("Invalid temperature")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).temperature > 0) {
            TYPE_WARM
        } else {
            TYPE_COLD
        }
    }
}

private fun getImage(weather: Weather) =
    "https://openweathermap.org/img/wn/${weather.iconURL}@2x.png"

class WeatherColdHolder(private val view: View) :
    RecyclerView.ViewHolder(view.rootView) {
    fun bind(weather: Weather) {
        val weatherTimeDate = weather.dtTxt.split(" ")
        view.findViewById<TextView>(R.id.tvTemperature).text =
            view.rootView.context.getString(R.string.temperature, weather.temperature.toString())
        view.findViewById<TextView>(R.id.tvTime).text = weatherTimeDate.first()
        view.findViewById<TextView>(R.id.tvDate).text = weatherTimeDate.last()
        view.findViewById<TextView>(R.id.tvPressure).text = weather.pressure.toString()
        Glide
            .with(view.rootView)
            .load(getImage(weather))
            .into(view.findViewById(R.id.ivIcon))
    }
}

class WeatherWarmHolder(private val view: View) :
    RecyclerView.ViewHolder(view.rootView) {
    fun bind(weather: Weather) {
        val weatherTimeDate = weather.dtTxt.split(" ")
        view.findViewById<TextView>(R.id.tvTemperature).text =
            view.rootView.context.getString(R.string.temperature, weather.temperature.toString())
        view.findViewById<TextView>(R.id.tvTime).text = weatherTimeDate.first()
        view.findViewById<TextView>(R.id.tvDate).text = weatherTimeDate.last()
        view.findViewById<TextView>(R.id.tvPressure).text = weather.pressure.toString()
        Glide
            .with(view.rootView)
            .load(getImage(weather))
            .into(view.findViewById(R.id.ivIcon))
    }
}

class WeatherDiffCallback : DiffUtil.ItemCallback<Weather>() {
    override fun areItemsTheSame(
        oldItem: Weather,
        newItem: Weather
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: Weather,
        newItem: Weather
    ): Boolean = oldItem == newItem
}