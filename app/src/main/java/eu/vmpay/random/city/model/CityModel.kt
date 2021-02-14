package eu.vmpay.random.city.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_table")
data class CityModel(
        val title: String,
        val color: String,
        val timestamp: Long,
        @PrimaryKey(autoGenerate = true) val uid: Long = 0
)
