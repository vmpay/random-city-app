package eu.vmpay.random.city.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import eu.vmpay.random.city.repository.Repository
import eu.vmpay.random.city.tools.LOADER_DELAY
import eu.vmpay.random.city.view.fragment.LoaderFragmentDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoaderViewModel(repository: Repository) : BaseViewModel(repository) {
    val ldNavigate = MutableLiveData<NavDirections>()

    init {
        viewModelScope.launch {
            delay(LOADER_DELAY)
            ldNavigate.value = LoaderFragmentDirections.actionLoaderFragmentToCityListFragment()
        }
    }
}
