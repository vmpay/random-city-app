package eu.vmpay.random.city.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import eu.vmpay.random.city.databinding.FragmentCityListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CityListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CityListFragment : Fragment() {
    private var _binding: FragmentCityListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        binding.btnNavigate.setOnClickListener {
            findNavController().navigate(CityListFragmentDirections.actionCityListFragmentToCityDetailsFragment())
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CityListFragment.
         */
        @JvmStatic
        fun newInstance() = CityListFragment()
    }
}