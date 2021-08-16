package com.franjo.android.bakingapp.data.remote.mapper

import com.franjo.android.bakingapp.domain.error.ErrorModel
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

// Partially -> https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
class NetworkMapperImpl @Inject constructor() : NetworkErrorMapper {

    override fun map(throwable: Throwable, errorType: Class<*>?) = when (throwable) {
        is IOException -> ErrorModel.NetworkError
        is HttpException -> {
            val statusCode = throwable.code()
            if (errorType == null) {
                errorModelByStatusCode(statusCode)
            } else {
                val errorResponse = convertErrorBody(throwable, errorType)
                ErrorModel.GenericError(statusCode, errorResponse)
            }
        }
        else -> {
            ErrorModel.Unknown
        }
    }

    private fun errorModelByStatusCode(code: Int) = when (code) {
        HttpURLConnection.HTTP_NOT_FOUND -> ErrorModel.NotFound
        HttpURLConnection.HTTP_FORBIDDEN -> ErrorModel.Forbidden
        HttpURLConnection.HTTP_UNAVAILABLE -> ErrorModel.Unavailable
        else -> ErrorModel.Unknown
    }

    private fun <T : Any> convertErrorBody(throwable: HttpException, clazz: Class<T>): T? {
        return try {
            throwable.response()?.errorBody()?.string()?.let { error ->
                val moshiAdapter = Moshi.Builder().build().adapter(clazz)
                moshiAdapter.fromJson(error)
            }
        } catch (exception: Exception) {
            null
        }
    }
}