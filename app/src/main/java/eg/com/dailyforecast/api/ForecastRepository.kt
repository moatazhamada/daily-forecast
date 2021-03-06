package eg.com.dailyforecast.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import eg.com.dailyforecast.db.dao.CityForecastDao
import eg.com.dailyforecast.db.dto.ForecastResponse
import eg.com.dailyforecast.db.entity.CityForecast
import eg.com.dailyforecast.utils.ConnectivityUtils
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class ForecastRepository @Inject constructor(
    private val gateway: ForecastGateway,
    private val cityForecastDao: CityForecastDao,
) {
    suspend fun getCityForecast(query: String): LiveData<ResultDto<ForecastResponse?>> {

        val forecastResponseMutableLiveData: MutableLiveData<ResultDto<ForecastResponse?>> =
            MutableLiveData()
        forecastResponseMutableLiveData.postValue(ResultDto.loading())
        try {
            if (ConnectivityUtils.isNetworkAvailable()) {
                val response = gateway.searchCity(query)
                if (response.code == "200") {
                    val jsonObject = Gson().toJson(response).toString()
                    cityForecastDao.insertCityForecast(CityForecast(query, jsonObject))
                    forecastResponseMutableLiveData.postValue(ResultDto.success(response))
                } else {
                    forecastResponseMutableLiveData.postValue(
                        ResultDto.error(
                            response.message,
                            response
                        )
                    )
                    retrieveFromDB(forecastResponseMutableLiveData, query)
                }
            } else {
                retrieveFromDB(forecastResponseMutableLiveData, query)
            }
        } catch (e: Exception) {
            forecastResponseMutableLiveData.postValue(
                ResultDto.error(
                    e.message ?: e.localizedMessage ?: e.toString()
                )
            )
        }
        return forecastResponseMutableLiveData
    }

    private suspend fun retrieveFromDB(
        forecastResponseMutableLiveData: MutableLiveData<ResultDto<ForecastResponse?>>,
        query: String
    ) {
        forecastResponseMutableLiveData.postValue(ResultDto.noInternet())
        val forecastData = cityForecastDao.getCityForecast(query)
        if (forecastData.isNotEmpty()) {
            val data =
                Gson().fromJson(
                    forecastData.first().forecast,
                    ForecastResponse::class.java
                )
            forecastResponseMutableLiveData.postValue(ResultDto.success(data))
        } else {
            forecastResponseMutableLiveData.postValue(ResultDto.error("No Data Available"))
        }
    }
}
