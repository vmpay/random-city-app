package eu.vmpay.random.city.viewmodel

import androidx.lifecycle.ViewModel
import eu.vmpay.random.city.repository.Repository

abstract class BaseViewModel(protected val repository: Repository) : ViewModel() {

}
