package com.nnapps.weatherapp.presentation

import com.nnapps.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val currentCity: String? = null,
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
