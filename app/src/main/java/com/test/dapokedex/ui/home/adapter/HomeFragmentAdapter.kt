package com.test.dapokedex.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.dapokedex.data.remote.model.PokeResult
import com.test.dapokedex.databinding.PokemonListItemBinding

class HomeFragmentAdapter(
    private val listener: (Int) -> Unit
) : ListAdapter<PokeResult, HomeFragmentAdapterViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentAdapterViewHolder {
        val binding = PokemonListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return HomeFragmentAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFragmentAdapterViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            pokemonNumberName.text = item.name

            holder.itemView.setOnClickListener {
                listener.invoke(position)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<PokeResult> =
            object : DiffUtil.ItemCallback<PokeResult>(){
                override fun areContentsTheSame(
                    oldItem: PokeResult,
                    newItem: PokeResult
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areItemsTheSame(
                    oldItem: PokeResult,
                    newItem: PokeResult
                ): Boolean {
                    return oldItem.name == newItem.name
                }
            }
    }
}

class HomeFragmentAdapterViewHolder(var binding: PokemonListItemBinding) : RecyclerView.ViewHolder(binding.root)

