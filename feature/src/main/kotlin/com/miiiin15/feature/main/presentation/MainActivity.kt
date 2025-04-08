package com.miiiin15.feature.main.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.miiiin15.feature.main.navigation.MainNavigator
import com.miiiin15.feature.main.navigation.rememberMainNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigator: MainNavigator = rememberMainNavigator()

            MainScreen(
                navigator = navigator
            )
        }
    }
}

