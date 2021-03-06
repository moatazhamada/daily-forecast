package eg.com.dailyforecast.api

import eg.com.dailyforecast.BuildConfig
import eg.com.dailyforecast.db.dto.ForecastResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Used to connect to the Forecast API to fetch weather
 */

interface ForecastGateway {
    @GET("forecast?")
    suspend fun searchCity(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") clientId: String = BuildConfig.OPEN_WEATHER_API_KEY
    ): ForecastResponse

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        private const val CONNECT_TIMEOUT = 60L // in secs
        private const val READ_TIMEOUT = 60L // in secs

        fun create(): ForecastGateway {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ForecastGateway::class.java)
        }
    }
}
