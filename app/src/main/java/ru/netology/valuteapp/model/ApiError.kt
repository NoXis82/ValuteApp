package ru.netology.valuteapp.model

import android.database.SQLException
import java.io.IOException
import java.net.ConnectException

sealed class ApiError {
    object UnknownException : ApiError()
    object ServerError : ApiError()
    object NetworkError : ApiError()
    companion object {
        fun from(e: Throwable): ApiError = when (e) {
            is ApiException -> e.error
            is ConnectException -> NetworkError
            else -> UnknownException
        }
    }
}

class ApiException(
    val error: ApiError,
    throwable: Throwable? = null
) : IOException(throwable)
