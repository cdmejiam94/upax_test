package com.test.dapokedex.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.dapokedex.data.remote.api.base.Result
import com.test.dapokedex.data.remote.feature.PokemonRemoteDataSource
import com.test.dapokedex.data.remote.model.PokeResult
import com.test.dapokedex.data.remote.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val dataSource: PokemonRemoteDataSource
) : ViewModel() {
    private val _pokemon = MutableLiveData<Pokemon?>()
    val pokemon: LiveData<Pokemon?> = _pokemon

    fun getPokemonList(id: Int) {
        viewModelScope.launch {
            dataSource.fetchPokemonData(id)
                .catch { it.message }
                .collect { result ->
                    if (result.status == Result.Status.SUCCESS) {
                        _pokemon.value = result.data
                    }
                }
        }
    }
}