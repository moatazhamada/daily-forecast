package eg.com.dailyforecast.db.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Weather(
    @SerializedName("id")
    val id: Int, // 800 => Weather condition id
    @SerializedName("main")
    val main: String, // Clear => Group of weather parameters (Rain, Snow, Extreme etc.)
    @SerializedName("description")
    val description: String, // clear sky => Weather condition within the group.
    @SerializedName("icon")
    val icon: String // 01n
)