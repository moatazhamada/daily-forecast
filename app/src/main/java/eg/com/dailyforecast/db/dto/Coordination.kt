package eg.com.dailyforecast.db.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Coordination(
    @SerializedName("lat")
    val lat: Double, // 30.0626
    @SerializedName("lon")
    val lon: Double // 31.2497
)