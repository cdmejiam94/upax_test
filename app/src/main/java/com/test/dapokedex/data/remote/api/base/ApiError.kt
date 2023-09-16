package com.test.dapokedex.data.remote.api.base

import com.google.gson.annotations.SerializedName

data class ApiError(
    @SerializedName("message")
    val message: String? = null,

    @SerializedName("isError")
    val isError: Boolean = false,

    @SerializedName("detail")
    val detail: String? = null
)