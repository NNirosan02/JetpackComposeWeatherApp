package com.nnapps.weatherapp.domain.repository

import com.nnapps.weatherapp.domain.util.Resource
import com.nnapps.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}