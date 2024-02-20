package com.example.weatherapp.data

import com.example.weatherapp.utils.constants.Constants.Companion.WEATHER_API_KEY
import com.example.weatherapp.data.remote.RemoteDataSource
import com.example.weatherapp.data.remote.response.WeatherDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RemoteDataSourceIml @Inject constructor(
    private val httpClient: HttpClient
) : RemoteDataSource {
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
    }

    override suspend fun getWeatherDataResponse(lat: Float, lng: Float): WeatherDataResponse {
        return httpClient
            .get("${BASE_URL}/weather?lat=$lat&lon=$lng&appid=${WEATHER_API_KEY}&units=metric")
            .body()
    }
}