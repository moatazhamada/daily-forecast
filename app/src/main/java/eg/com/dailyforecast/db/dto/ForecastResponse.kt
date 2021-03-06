package eg.com.dailyforecast.db.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ForecastResponse(
    @SerializedName("cod")
    val code: String, // "200"
    @SerializedName("message")
    val message: String, // "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."
    @SerializedName("cnt")
    val count: Int, // 40  => A number of timestamps returned in the API response
    @SerializedName("list")
    val list: List<ForecastAtTime>,
    @SerializedName("city")
    val city: City
)