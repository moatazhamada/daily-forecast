package eg.com.dailyforecast.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import eg.com.dailyforecast.db.dto.ForecastResponse
import java.util.Calendar

/**
 * [CityForecast] represents when a user search for a [cityName]
 */
@Entity(
    tableName = "forecast",
    indices = [Index("id")]
)
data class CityForecast(
    @ColumnInfo(name = "city_name")
    val cityName: String,
    @ColumnInfo(name = "forecast")
    val forecast: String,
    @ColumnInfo(name = "last_searching_date")
    val lastSearchingDate: Calendar = Calendar.getInstance()
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
