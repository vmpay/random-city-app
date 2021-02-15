package eu.vmpay.random.city.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import eu.vmpay.random.city.R
import eu.vmpay.random.city.databinding.FragmentCityDetailsBinding
import eu.vmpay.random.city.tools.getFixedCity
import eu.vmpay.random.city.tools.getFixedColor
import eu.vmpay.random.city.view.MainActivity
import eu.vmpay.random.city.viewmodel.CityDetailsViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CityDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CityDetailsFragment : BaseFragment(), OnMapReadyCallback {
    private var _binding: FragmentCityDetailsBinding? = null
    private val viewModel: CityDetailsViewModel by viewModels { factory }
    private val args: CityDetailsFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentCityDetailsBinding.inflate(inflater, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@CityDetailsFragment)
        // Passing only uid may help us in future with dynamic links integration
        viewModel.apply {
            getCityById(args.uid)
            ldCityDetails.observe(viewLifecycleOwner, {
                Log.d("CityDetailsFragment", "City $it")
                (activity as? MainActivity)?.setToolbarTitleColor(it.title, it.color.getFixedColor())
                moveCamera()
            })
        }
        return binding.root
    }

    override fun onDestroyView() {
        (activity as? MainActivity)?.setToolbarTitleColor()
        super.onDestroyView()
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        moveCamera()
    }

    private fun moveCamera() {
        if (this::mMap.isInitialized)
            viewModel.ldCityDetails.value?.title?.getFixedCity()?.let { latLng ->
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
            }
    }
}
