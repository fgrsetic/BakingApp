package com.franjo.android.bakingapp.data.utils

import com.franjo.android.bakingapp.data.remote.mapper.NetworkErrorMapper
import com.franjo.android.bakingapp.domain.utils.ResultWrapper
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NetworkUtil @Inject constructor(private val networkErrorMapper: NetworkErrorMapper) {

  suspend fun <T> safeApiCall(
    coroutineContext: CoroutineContext,
    apiCall: suspend () -> T,
    errorType: Class<*>? = null
  ): ResultWrapper<T> {
    return withContext(coroutineContext) {
      try {
        ResultWrapper.Success(apiCall())
      } catch (throwable: Throwable) {
        ResultWrapper.Error(networkErrorMapper.map(throwable, errorType))
      }
    }
  }
}