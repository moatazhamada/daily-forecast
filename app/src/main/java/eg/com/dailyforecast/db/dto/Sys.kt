package eg.com.dailyforecast.db.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Sys(
    @SerializedName("pod")
    val pod: String // n => Part of the day (n - night, d - day)
)