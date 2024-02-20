package com.example.weatherapp.data.repository

import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.data.remote.RemoteDataSource
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Float, lgn: Float): WeatherModel {
        val response = remoteDataSource.getWeatherDataResponse(lat, lgn)
        val weather = response.weather.first()

        return WeatherModel(
            locationName = response.name,
            conditionIcon = weather.icon,
            condition = weather.main,
            temperature = response.main.temp.roundToInt(),
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            isDay = weather.icon.last() == 'd'
        )

    }
}