package eu.vmpay.random.city.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import eu.vmpay.random.city.model.CityModel
import eu.vmpay.random.city.repository.Repository
import eu.vmpay.random.city.tools.Event
import kotlinx.coroutines.launch

class CityDetailsViewModel(repository: Repository) : BaseViewModel(repository) {

    val ldCityDetails = MutableLiveData<CityModel>()

    fun getCityById(uid: Long) {
        viewModelScope.launch {
            val cityModel = repository.getCityByUid(uid)
            if (cityModel != null) {
                ldCityDetails.value = cityModel
            } else {
                val message = "Item not found"
                Log.e("CityDetailsViewModel", message)
                isError.value = Event(message)
            }
        }
    }
}