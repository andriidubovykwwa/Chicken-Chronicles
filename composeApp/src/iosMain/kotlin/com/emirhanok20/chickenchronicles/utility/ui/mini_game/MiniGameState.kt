package com.emirhanok20.chickenchronicles.utility.ui.mini_game

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

data class MiniGameState(
    val wormOffset: IntOffset? = null,
    val gameSize: IntSize = IntSize.Zero,
    val isGameStarted: Boolean = false,
    val isGameEnded: Boolean = false
)
