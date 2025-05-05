package com.devname.chickenchronicles

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.devname.chickenchronicles.di.appModule
import com.devname.chickenchronicles.game.GameScreen
import com.devname.chickenchronicles.utility.MathExpressionGenerator
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        MaterialTheme {
            repeat(20) {
                println(MathExpressionGenerator.generateExpression())
            }
            GameScreen()
        }
    }
}