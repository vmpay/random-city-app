package eu.vmpay.random.city.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.vmpay.random.city.repository.Repository
import eu.vmpay.random.city.tools.Event

abstract class BaseViewModel(protected val repository: Repository) : ViewModel() {

    // If we are expecting some error message we can deliver it to user using this LiveData
    val isError = MutableLiveData<Event<String>>()
}
