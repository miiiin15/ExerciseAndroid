package com.miiiin15.feature.home.domain.di

import com.miiiin15.feature.home.data.impl.LocalRepositoryImpl
import com.miiiin15.feature.home.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLocalRepository(repository: LocalRepositoryImpl): LocalRepository
}