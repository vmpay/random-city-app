package eu.vmpay.random.city.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.vmpay.random.city.repository.Repository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoaderViewModel::class.java -> {
                LoaderViewModel() as T
            }
            CityListViewModel::class.java -> {
                CityListViewModel() as T
            }
            CityDetailsViewModel::class.java -> {
                CityDetailsViewModel() as T
            }
            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }
}