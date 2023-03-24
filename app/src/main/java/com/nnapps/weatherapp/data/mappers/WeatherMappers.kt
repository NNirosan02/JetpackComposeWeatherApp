package com.nnapps.weatherapp.data.mappers

import com.nnapps.weatherapp.data.remote.WeatherDataDto
import com.nnapps.weatherapp.data.remote.WeatherDto
import com.nnapps.weatherapp.domain.weather.WeatherData
import com.nnapps.weatherapp.domain.weather.WeatherInfo
import com.nnapps.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed{ index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data =         WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data}
    }. also { print (it.values) }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val currentWeatherData = weatherDataMap[0]?.find {
        it.time.hour == currentTime()
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}

fun currentTime(): Int {
    val now = LocalDateTime.now()
    val hour = when {
        now.minute < 30 -> now.hour
        now.hour == 23 -> 12
        else -> now.hour + 1
    }
    return hour
}