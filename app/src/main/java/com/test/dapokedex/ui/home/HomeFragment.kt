package com.test.dapokedex.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.dapokedex.MainActivity
import com.test.dapokedex.R
import com.test.dapokedex.databinding.FragmentHomeBinding
import com.test.dapokedex.ui.detail.DetailFragment
import com.test.dapokedex.ui.home.adapter.HomeFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() =_binding!!

    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

    private var homeFragmentAdaper: HomeFragmentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeFragmentAdaper = HomeFragmentAdapter {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container,DetailFragment.newInstance(it))
                commit()
            }
        }

        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = homeFragmentAdaper
            }
        }

        homeFragmentViewModel.pokemonList.observe(viewLifecycleOwner) {
            if (it!=null){
                homeFragmentAdaper?.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }
    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}