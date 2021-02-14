package eu.vmpay.random.city.view.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {

    fun navigateTo(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }
}
