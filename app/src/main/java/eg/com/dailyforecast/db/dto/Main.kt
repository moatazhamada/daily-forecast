package eg.com.dailyforecast.db.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Main(
    @SerializedName("temp")
    val temp: Double, //  14.16
    @SerializedName("feels_like")
    val feelsLike: Double, // 10.62
    @SerializedName("temp_min")
    val tempMin: Double, //  13.76
    @SerializedName("temp_max")
    val tempMax: Double, //  14.16
    @SerializedName("pressure")
    val pressure: Int, // 1024
    @SerializedName("sea_level")
    val seaLevel: Int, // 1024
    @SerializedName("grnd_level")
    val grndLevel: Int, // 1020
    @SerializedName("humidity")
    val humidity: Int, // 62
    @SerializedName("temp_kf")
    val tempKf: Double // 0.09
)