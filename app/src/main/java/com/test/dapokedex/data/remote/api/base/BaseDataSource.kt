package com.test.dapokedex.data.remote.api.base

import com.google.gson.Gson
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }

            return Result.error(parseError(response)?.message ?: GENERIC_ERROR_MESSAGE)
        } catch (e: Exception) {
            return Result.error(GENERIC_ERROR_MESSAGE)
        }
    }

    private fun parseError(response: Response<*>?): ApiError? {
        response?.errorBody()?.string().let {
            return Gson().fromJson(it, ApiError::class.java)
        }
    }

    companion object {
        const val GENERIC_ERROR_MESSAGE = "We are unable to retrieve your information at this time, please try again later."
    }
}
