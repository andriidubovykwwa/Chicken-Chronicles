package com.emirhanok20.chickenchronicles.utility.ui.mini_game

import androidx.compose.ui.unit.IntSize

sealed interface MiniGameEvent {
    data class SetGameSize(val size: IntSize) : MiniGameEvent
    data object ClickOnWarm : MiniGameEvent
    data object Start : MiniGameEvent
    data object EndGame : MiniGameEvent
    data object Reset : MiniGameEvent
}