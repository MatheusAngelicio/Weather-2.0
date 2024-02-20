package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.WeatherModel

interface WeatherRepository {

    suspend fun getWeatherData(lat: Float, lgn: Float): WeatherModel
}