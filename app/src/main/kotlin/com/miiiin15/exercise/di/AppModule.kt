package com.miiiin15.exercise.di

import android.app.Application
import com.miiiin15.exercise.ExerciseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(application: Application): String =
        (application as ExerciseApplication).baseUrl

    @Provides
    @Named("clientId")
    fun provideClientId(application: Application): String =
        (application as ExerciseApplication).clientId

    @Provides
    @Named("clientSecret")
    fun provideClientSecret(application: Application): String =
        (application as ExerciseApplication).clientSecret
}