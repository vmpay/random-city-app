package eu.vmpay.random.city.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import eu.vmpay.random.city.databinding.FragmentCityDetailsBinding
import eu.vmpay.random.city.viewmodel.CityDetailsViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CityDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CityDetailsFragment : BaseFragment() {
    private var _binding: FragmentCityDetailsBinding? = null
    private val viewModel: CityDetailsViewModel by viewModels { factory }
    private val args: CityDetailsFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentCityDetailsBinding.inflate(inflater, container, false)
        // Passing only uid may help us in future with dynamic links integration
        viewModel.apply {
            getCityById(args.uid)
            ldCityDetails.observe(viewLifecycleOwner, {
                Log.d("CityDetailsFragment", "City $it")
            })
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CityDetailsFragment.
         */
        @JvmStatic
        fun newInstance() = CityDetailsFragment()
    }
}
