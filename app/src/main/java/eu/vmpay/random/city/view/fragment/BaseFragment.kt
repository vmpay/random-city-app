package eu.vmpay.random.city.view.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import eu.vmpay.random.city.viewmodel.ViewModelFactory
import org.koin.android.ext.android.inject

abstract class BaseFragment : Fragment() {
    protected val factory: ViewModelFactory by inject()

    fun navigateTo(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }
}
