package com.example.retrofitforecaster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitforecaster.databinding.ItemWeatherColdHolderBinding
import com.example.retrofitforecaster.databinding.ItemWeatherWarmHolderBinding
import java.lang.IllegalArgumentException

private const val TYPE_COLD = 0
private const val TYPE_WARM = 1

class Adapter:
    ListAdapter<WeatherData.DataWeather, RecyclerView.ViewHolder>(WeatherDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_COLD -> {
                val bindingCold = ItemWeatherColdHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return WeatherColdHolder(bindingCold)
            }
            TYPE_WARM -> {
                val bindingWarm = ItemWeatherWarmHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return WeatherWarmHolder(bindingWarm)
            }
            else -> {
                throw IllegalArgumentException("Missing type of holder")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_COLD -> (holder as WeatherColdHolder).bind(getItem(position))
            TYPE_WARM -> (holder as WeatherWarmHolder).bind(getItem(position))
            else -> throw IllegalArgumentException("Invalid temperature")
        }
    }

    override fun getItemViewType(position: Int): Int = if (getItem(position).main.temp < -5) {
        TYPE_COLD
    } else {
        TYPE_WARM
    }
}

private fun getImage(weather: WeatherData.DataWeather) =
    "https://openweathermap.org/img/wn/${weather.weather.first().icon}@2x.png"

class WeatherColdHolder(private val binding: ItemWeatherColdHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(weather: WeatherData.DataWeather) {
        binding.tvTemperature.text =
            binding.root.context.getString(R.string.temperature, weather.main.temp.toString())
        binding.tvDate.text = weather.dtTxt
        binding.tvPressure.text = weather.main.pressure.toString()
        Glide
            .with(binding.root)
            .load(getImage(weather))
            .into(binding.ivIcon)
    }
}

class WeatherWarmHolder(private val binding: ItemWeatherWarmHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(weather: WeatherData.DataWeather) {
        binding.tvTemperature.text =
            binding.root.context.getString(R.string.temperature, weather.main.temp.toString())
        binding.tvDate.text = weather.dtTxt
        binding.tvPressure.text = weather.main.pressure.toString()
        Glide
            .with(binding.root)
            .load(getImage(weather))
            .into(binding.ivIcon)
    }
}

class WeatherDiffCallback : DiffUtil.ItemCallback<WeatherData.DataWeather>() {
    override fun areItemsTheSame(
        oldItem: WeatherData.DataWeather,
        newItem: WeatherData.DataWeather
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: WeatherData.DataWeather,
        newItem: WeatherData.DataWeather
    ): Boolean = oldItem == newItem
}