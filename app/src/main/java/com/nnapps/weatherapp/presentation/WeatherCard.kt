package com.nnapps.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${
                        data.time.format(
                            DateTimeFormatter.ofPattern("HH:mm")
                        )
                    }",
                    modifier = Modifier.align(Alignment.End), color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = data.weatherType.weatherDesc,
                    modifier = Modifier.width(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = data.weatherType.weatherDesc, fontSize = 20.sp, color = Color.White)
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.toInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(
                            id = com.nnapps.weatherapp.R.drawable.ic_pressure
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.humidity.toInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(
                            id = com.nnapps.weatherapp.R.drawable.ic_drop
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.toInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(
                            id = com.nnapps.weatherapp.R.drawable.ic_wind
                        ),
                        iconTint = Color.White,
                        textStyle = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}