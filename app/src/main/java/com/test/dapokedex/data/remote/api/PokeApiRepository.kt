package com.test.dapokedex.data.remote.api

import com.test.dapokedex.data.remote.api.base.BaseDataSource
import com.test.dapokedex.data.remote.api.base.Result
import com.test.dapokedex.data.remote.model.Pokemon
import retrofit2.Retrofit
import javax.inject.Inject

class PokeApiRepository @Inject constructor(
    private val retrofit: Retrofit
) : BaseDataSource() {
    suspend fun fetchPokemonList(limit: Int, offset: Int) : Result<PokeApiResponse?> {
        val pokemonService = retrofit.create(PokeApiService::class.java)
        return getResult { pokemonService.getPokemonList(limit, offset) }
    }

    suspend fun fetchPokemonData(id: Int): Result<Pokemon?> {
        val pokemonService = retrofit.create(PokeApiService::class.java)
        return getResult { pokemonService.getPokemonInfo(id) }
    }
}