package com.miiiin15.exercise

import android.app.Application
import androidx.annotation.CallSuper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExerciseApplication : Application(){
    val baseUrl: String get() = "https://openapi.naver.com/v1/search/"
    val clientId: String get() = BuildConfig.NAVER_CLIENT_ID
    val clientSecret: String get() = BuildConfig.NAVER_CLIENT_SECRET

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance: ExerciseApplication
        private set
    }
}