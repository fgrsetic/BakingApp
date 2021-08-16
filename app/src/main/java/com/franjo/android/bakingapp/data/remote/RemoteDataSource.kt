package com.franjo.android.bakingapp.data.remote

import com.franjo.android.bakingapp.data.remote.service.ApiService
import com.franjo.android.bakingapp.data.utils.NetworkUtil
import com.franjo.android.bakingapp.domain.di.IODispatcher
import com.franjo.android.bakingapp.domain.mapper.EntityMapper
import com.franjo.android.bakingapp.domain.model.Recipe
import com.franjo.android.bakingapp.domain.utils.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val networkUtil: NetworkUtil,
    private val mapper: EntityMapper,
    @IODispatcher val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllRecipes(): ResultWrapper<Recipe> =
        networkUtil.safeApiCall((dispatcher), { mapper.apiToDomain(apiService.getAllRecipes()) })

}


