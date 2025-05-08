package com.emirhanok20.chickenchronicles

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.emirhanok20.chickenchronicles.di.appModule
import com.emirhanok20.chickenchronicles.navigation.MainScreen
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        MaterialTheme {
            MainScreen()
        }
    }
}