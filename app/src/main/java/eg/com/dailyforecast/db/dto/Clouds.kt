package eg.com.dailyforecast.db.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Clouds(
    @SerializedName("all")
    val all: Double // 0 Cloudiness, %

)