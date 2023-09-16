package com.test.dapokedex.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.test.dapokedex.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment(private val position: Int): Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() =_binding!!

    private val detailFragmentViewModel: DetailFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailFragmentViewModel.getPokemonList(position + 1)

        detailFragmentViewModel.pokemon.observe(viewLifecycleOwner) {
            if (it!=null){
                Glide.with(this)
                    .load(it.sprites.frontDefault)
                    .into(binding.cardImage)

                binding.apply {
                    pokemonNumberName.editText?.setText("#${it.id} ${it.name}")
                    pokemonType1.editText?.setText(it.types.first().type.name)
                    if (it.types.size == 2) {
                        pokemonType2.editText?.setText(it.types.get(1).type.name)
                    } else {
                        pokemonType2.visibility = View.GONE
                    }
                    pokemonWeight.editText?.setText("${it.weight} libs")
                    pokemonHeight.editText?.setText("${it.height} ft")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(position: Int): DetailFragment {
            val fragment = DetailFragment(position)
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}