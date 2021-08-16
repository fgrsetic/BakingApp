package com.franjo.android.bakingapp.data.remote.mapper

import com.franjo.android.bakingapp.domain.error.ErrorModel


// map response error to appropriate model
interface NetworkErrorMapper {
  fun map(throwable: Throwable, errorType: Class<*>?): ErrorModel
}
