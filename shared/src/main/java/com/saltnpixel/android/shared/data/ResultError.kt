package com.saltnpixel.android.shared.data

import com.google.gson.annotations.SerializedName

sealed class ResultError
data class EmptyError(val msg: String): ResultError()
data class ServerError(val msg: String): ResultError()
data class FailError(val error:String,
                     @SerializedName("message")
                     val msg:String):ResultError()