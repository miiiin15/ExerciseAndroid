package com.miiiin15.feature.home.data.impl

import com.miiiin15.base.domain.result.ResponseResult
import com.miiiin15.feature.home.data.remote.HomeApiService
import com.miiiin15.feature.home.data.remote.model.toDomainModel
import com.miiiin15.feature.home.domain.model.LocalItem
import com.miiiin15.feature.home.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val homeApiService: HomeApiService
) : LocalRepository {
    override suspend fun searchLocal(
        keyword: String,
        display: Int,
        start:Int,
    ): ResponseResult<List<LocalItem>>{
     return try {
            val response = homeApiService.searchLocal(keyword, display)
         ResponseResult.Success(response.items.map { it.toDomainModel() })
        } catch (e: Exception) {
            ResponseResult.Failure(e)
     }
    }
}