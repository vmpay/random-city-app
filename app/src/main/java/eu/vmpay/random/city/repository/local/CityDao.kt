package eu.vmpay.random.city.repository.local

import androidx.room.Dao
import androidx.room.Query
import eu.vmpay.random.city.model.CityModel
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CityDao : BaseDao<CityModel>() {
    @Query("SELECT * FROM city_table ORDER BY title")
    abstract fun getCityList(): Flow<List<CityModel>>

    @Query("SELECT * FROM city_table WHERE uid LIKE :uid")
    abstract suspend fun getCityById(uid: Long): CityModel?
}
