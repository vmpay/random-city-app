package eu.vmpay.random.city.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import eu.vmpay.random.city.model.CityModel
import eu.vmpay.random.city.repository.Repository
import eu.vmpay.random.city.tools.ERROR_MESSAGE
import eu.vmpay.random.city.tools.Event
import kotlinx.coroutines.launch

class CityDetailsViewModel(repository: Repository) : BaseViewModel(repository) {

    val ldCityDetails = MutableLiveData<CityModel>()

    fun getCityById(uid: Long) {
        viewModelScope.launch {
            try {
                val cityModel = repository.getCityByUid(uid)
                if (cityModel != null) ldCityDetails.value = cityModel
                else isError.value = Event(ERROR_MESSAGE)
            } catch (e: Throwable) {
                // we may parse and log exception properly
                // but for the sake of simplicity we won't do it
                isError.value = Event(ERROR_MESSAGE)
            }
        }
    }
}