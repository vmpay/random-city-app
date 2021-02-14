package eu.vmpay.random.city.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.vmpay.random.city.model.CityModel
import eu.vmpay.random.city.tools.DATABASE_NAME
import eu.vmpay.random.city.tools.DATABASE_VERSION

@Database(entities = [CityModel::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, DATABASE_NAME
            )
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}
