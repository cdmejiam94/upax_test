package com.test.dapokedex.data.remote.api

import com.test.dapokedex.data.remote.model.Pokemon
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(@Path("id") id: Int): Response<Pokemon>
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<PokeApiResponse?>
}