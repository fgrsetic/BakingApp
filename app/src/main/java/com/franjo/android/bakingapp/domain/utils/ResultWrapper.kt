package com.franjo.android.bakingapp.domain.utils

import com.franjo.android.bakingapp.domain.error.ErrorModel

sealed class ResultWrapper<out T> {
  data class Success<out T>(val data: T) : ResultWrapper<T>()
  data class Error(val error: ErrorModel) : ResultWrapper<Nothing>()
}
