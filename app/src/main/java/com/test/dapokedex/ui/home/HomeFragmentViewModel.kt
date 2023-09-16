package com.test.dapokedex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.dapokedex.data.remote.api.base.Result
import com.test.dapokedex.data.remote.feature.PokemonRemoteDataSource
import com.test.dapokedex.data.remote.model.PokeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val dataSource: PokemonRemoteDataSource
) : ViewModel() {
    private val _pokemonList = MutableLiveData<List<PokeResult>>()
    val pokemonList: LiveData<List<PokeResult>> = _pokemonList

    var currentOffset = 0

    init {
        getPokemonList(currentOffset)
    }

    fun getPokemonList(offset: Int) {
        viewModelScope.launch {
            currentOffset = offset
            dataSource.fetchPokemon(25, offset)
                .catch { it.message }
                .collect { result ->
                    if (result.status == Result.Status.SUCCESS) {
                        _pokemonList.value = result.data?.results
                    }
                }
        }
    }
}