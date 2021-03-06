/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eg.com.dailyforecast.api

import com.google.gson.Gson
import eg.com.dailyforecast.db.dao.CityForecastDao
import eg.com.dailyforecast.db.dto.ForecastResponse
import eg.com.dailyforecast.db.entity.CityForecast
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val gateway: ForecastGateway,
    private val cityForecastDao: CityForecastDao,
) {

    suspend fun getCityForecast(query: String): ForecastResponse {
        refreshUser(query)
        val forecastData = cityForecastDao.getCityForecast(query).first().forecast
        return Gson().fromJson(forecastData, ForecastResponse::class.java)
    }

    private suspend fun refreshUser(query: String) {
        val cityExists = cityForecastDao.getCityForecast(query)
        if (cityExists.isEmpty()) {
            val response = gateway.searchCity(query)
            cityForecastDao.insertCityForecast(CityForecast())
        }
    }

    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }

}
