package eg.com.dailyforecast.db.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class City(
    @SerializedName("id")
    val id: Int, // 360630
    @SerializedName("name")
    val name: String, // Cairo
    @SerializedName("coord")
    val coordination: Coordination,
    @SerializedName("country")
    val country: String, // EG
    @SerializedName("population")
    val population: Int, // 7734614
    @SerializedName("timezone")
    val timezone: Int, // 7200
    @SerializedName("sunrise")
    val sunriseTimestamp: Int, // 1614917805
    @SerializedName("sunset")
    val sunsetTimestamp: Int // 1614959793
)