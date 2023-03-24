package com.nnapps.weatherapp.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
    suspend fun getCurrentCity(lat: Double, long: Double): String?
}