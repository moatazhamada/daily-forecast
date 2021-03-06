package eg.com.dailyforecast.db.dao

import androidx.room.*
import eg.com.dailyforecast.db.entity.CityForecast
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [CityForecast] class.
 */
@Dao
interface CityForecastDao {
    @Query("SELECT * FROM forecast WHERE city_name = :cityName LIMIT 1")
    suspend fun getCityForecast(cityName:String): List<CityForecast>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCityForecast(cityForecast: CityForecast)

}
