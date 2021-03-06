package eg.com.dailyforecast.db.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ForecastAtTime (
    @SerializedName("dt")
    val timestampUnix: Int, // 1614988800 Time of data forecasted, unix, UTC
    @SerializedName("main")
    val main: Main,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("visibility")
    val visibility: Int, // 10000 => Average visibility, metres
    @SerializedName("pop")
    val pop: Double, // 0 => Probability of precipitation
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("dt_txt")
    val timestampText: String // 2021-03-06 00:00:00
)