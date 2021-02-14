package eu.vmpay.random.city.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import eu.vmpay.random.city.model.CityModel
import eu.vmpay.random.city.repository.Repository
import eu.vmpay.random.city.tools.Event
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CityListViewModel(repository: Repository) : BaseViewModel(repository) {

    val ldCityList = MutableLiveData<List<CityModel>>()

    init {
        repository.getCityList()
                .catch {
                    Log.e("CityListViewModel", it.stackTraceToString())
                    isError.value = Event("Some user readable message goes here")
                }
                .onEach { ldCityList.value = it }
                .launchIn(viewModelScope)
    }
}