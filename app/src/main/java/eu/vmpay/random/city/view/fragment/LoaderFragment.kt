package eu.vmpay.random.city.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import eu.vmpay.random.city.databinding.FragmentLoaderBinding
import eu.vmpay.random.city.viewmodel.LoaderViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [LoaderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoaderFragment : BaseFragment() {
    private var _binding: FragmentLoaderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoaderBinding.inflate(inflater, container, false)
        val viewModel: LoaderViewModel by viewModels()
        viewModel.ldNavigate.observe(viewLifecycleOwner, {
            navigateTo(it)
        })
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
         * @return A new instance of fragment LoaderFragment.
         */
        @JvmStatic
        fun newInstance() = LoaderFragment()
    }
}