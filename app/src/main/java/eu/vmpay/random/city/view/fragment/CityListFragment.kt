package eu.vmpay.random.city.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import eu.vmpay.random.city.R
import eu.vmpay.random.city.databinding.FragmentCityListBinding
import eu.vmpay.random.city.databinding.FragmentCityListTabletBinding
import eu.vmpay.random.city.view.MainActivity
import eu.vmpay.random.city.view.adapter.CityListAdapter
import eu.vmpay.random.city.viewmodel.CityListViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CityListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CityListFragment : BaseFragment() {
    private val viewModel: CityListViewModel by viewModels { factory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as? MainActivity)?.showToolbar()
        val rootView: View?
        if (context?.resources?.getBoolean(R.bool.isTablet) == true) {
            val binding = FragmentCityListTabletBinding.inflate(inflater, container, false)
            val adapter = CityListAdapter() {
                val navHostFragment = childFragmentManager.findFragmentById(R.id.profile_nav_container) as NavHostFragment
                navHostFragment.navController.navigate(CityDetailsFragmentDirections.actionCityDetailsFragmentSelf(it.uid))
            }
            binding.rvList.adapter = adapter
            viewModel.ldCityList.observe(viewLifecycleOwner, {
                it.forEach { Log.d("CityListFragment", "Mobile $it") }
                adapter.submitList(it)
            })
            rootView = binding.root
        } else {
            val binding = FragmentCityListBinding.inflate(inflater, container, false)
            val adapter = CityListAdapter() {
                findNavController().navigate(CityListFragmentDirections.actionCityListFragmentToCityDetailsFragment(it.uid))
            }
            binding.rvList.adapter = adapter
            viewModel.ldCityList.observe(viewLifecycleOwner, {
                it.forEach { Log.d("CityListFragment", "Tablet $it") }
                adapter.submitList(it)
            })
            rootView = binding.root
        }
        return rootView
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