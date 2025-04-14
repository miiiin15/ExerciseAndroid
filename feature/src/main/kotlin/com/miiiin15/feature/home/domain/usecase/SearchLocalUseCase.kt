package com.miiiin15.feature.home.domain.usecase

import com.miiiin15.feature.home.domain.repository.LocalRepository
import javax.inject.Inject

class SearchLocalUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(keyword: String, display: Int = 10, start: Int = 1) =
        localRepository.searchLocal(keyword, display, start)
}