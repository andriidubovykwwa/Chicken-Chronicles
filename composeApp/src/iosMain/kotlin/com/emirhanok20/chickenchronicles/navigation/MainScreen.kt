package com.emirhanok20.chickenchronicles.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.emirhanok20.chickenchronicles.navigation.game.GameScreen
import com.emirhanok20.chickenchronicles.navigation.loading.LoadingScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val onEvent = viewModel::onEvent

    LaunchedEffect(Unit) {
        onEvent(MainEvent.Start)
    }

    Crossfade(
        targetState = state.currentScreen,
        animationSpec = tween(700, easing = LinearOutSlowInEasing)
    ) {
        when (it) {
            Screen.LOADING -> LoadingScreen()
            Screen.GAME -> GameScreen()
        }
    }
}
