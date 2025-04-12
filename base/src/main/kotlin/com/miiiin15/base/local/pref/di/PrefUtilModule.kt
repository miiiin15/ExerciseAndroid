package com.miiiin15.base.local.pref.di

import com.miiiin15.base.local.pref.PrefUtil
import com.miiiin15.base.local.pref.impl.PrefUtilImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PrefUtilModule {

    @Binds
    @Singleton
    internal abstract fun bindPrefUtil(
        impl: PrefUtilImpl
    ): PrefUtil
}