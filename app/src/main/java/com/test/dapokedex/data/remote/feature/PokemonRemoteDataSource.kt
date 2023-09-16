package com.test.dapokedex.data.remote.feature

import com.test.dapokedex.data.remote.api.PokeApiRepository
import com.test.dapokedex.data.remote.api.PokeApiResponse
import com.test.dapokedex.data.remote.api.base.Result
import com.test.dapokedex.data.remote.model.PokeResult
import com.test.dapokedex.data.remote.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    private val retrofit: PokeApiRepository
){
    suspend fun fetchPokemon(limit: Int, offset: Int): Flow<Result<PokeApiResponse?>> =
        flow {
            emit(Result.loading())
            val result = retrofit.fetchPokemonList(limit, offset)
            emit(result)
        }.flowOn(Dispatchers.IO)

    suspend fun fetchPokemonData(id: Int): Flow<Result<Pokemon?>> =
        flow {
            emit(Result.loading())
            val result = retrofit.fetchPokemonData(id)
            emit(result)
        }.flowOn(Dispatchers.IO)
}