package eu.vmpay.random.city.repository

import android.util.Log
import eu.vmpay.random.city.model.CityModel
import eu.vmpay.random.city.repository.local.CityDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch


class Repository(private val channel: Channel<Pair<String, String>>, private val cityDao: CityDao) {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        Log.d("Repository", "init")
        applicationScope.launch {
            while (!channel.isClosedForSend) {
                val pair = channel.receive()
                // TODO store in DB
                Log.d("Repository", "Received $pair")
                cityDao.insert(CityModel(pair.first, pair.second, System.currentTimeMillis()))
            }
        }
    }

    fun getCityList() = cityDao.getCityList()

    /**
     * Method documentation example
     *
     * This suspend function gets @see[CityModel] from local data source
     *
     * @param uid unique identifier of the city in the data source
     * @return instance of @see[CityModel] if match the uid, otherwise - null
     */
    suspend fun getCityByUid(uid: Long) = cityDao.getCityById(uid)
}
