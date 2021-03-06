package eg.com.dailyforecast.db.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Wind(
    @SerializedName("speed")
    val speed: Double, // 4.05
    @SerializedName("deg")
    val deg: Double // 38
)