package com.example.weatherapp.ui.pages.weather

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.ui.theme.BlueSky
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherRoute(
    viewModel: WeatherViewModel = viewModel()
) {
    val state by viewModel.weatherInfoState.collectAsStateWithLifecycle()
    WeatherScreen(weatherModel = state.weatherModel)
}

@Composable
fun WeatherScreen(
    weatherModel: WeatherModel?,
 ) {
    weatherModel?.let {weatherInfo ->

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if (weatherInfo.isDay) {
                BlueSky
            } else Color.DarkGray
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = weatherInfo.locationName,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = weatherInfo.dayOfWeek,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(32.dp))

                val context : Context = LocalContext.current
                val iconDrawableResId: Int = context.resources.getIdentifier(
                    "weather_${weatherInfo.conditionIcon}",
                    "drawable",
                    context.packageName
                )

                Image(
                    painter = painterResource(id = iconDrawableResId),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = weatherInfo.condition,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "${weatherInfo.temperature}°",
                    color = Color.White,
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    }
    
}

@Preview
@Composable
fun WeatherScreenPreview() {
    WeatherAppTheme {
        WeatherScreen( weatherModel = WeatherModel(
            locationName = "Belo Horizonte",
            conditionIcon = "13n",
            condition = "Cloudy",
            temperature = 32,
            dayOfWeek = "Saturday",
            isDay = true,
        ))
    }
}