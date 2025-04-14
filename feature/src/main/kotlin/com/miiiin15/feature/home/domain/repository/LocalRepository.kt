package com.miiiin15.feature.home.domain.repository

import com.miiiin15.base.domain.result.ResponseResult
import com.miiiin15.feature.home.domain.model.LocalItem

interface LocalRepository {
    suspend fun searchLocal(
        keyword: String,
        display: Int = 10,
        start: Int = 1
    ): ResponseResult<List<LocalItem>>
}