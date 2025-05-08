package com.emirhanok20.chickenchronicles.utility.ui.mini_game

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MiniGameViewModel : ViewModel() {
    private val _state = MutableStateFlow(MiniGameState())
    val state = _state.asStateFlow()

    fun onEvent(event: MiniGameEvent) {
        when (event) {
            is MiniGameEvent.ClickOnWarm -> processClickOnWarm()
            is MiniGameEvent.SetGameSize -> processSetGameSize(event.size)
            is MiniGameEvent.Start -> processStart()
            is MiniGameEvent.Reset -> processReset()
            is MiniGameEvent.EndGame -> processEndGame()
        }
    }

    private fun processEndGame() = viewModelScope.launch {
        _state.update { it.copy(isGameEnded = true, isGameStarted = false, wormOffset = null) }
    }

    private fun processReset() = viewModelScope.launch {
        _state.update { MiniGameState() }
    }

    private fun processStart() = viewModelScope.launch {
        val gameSize = state.value.gameSize
        if (gameSize == IntSize.Zero) return@launch
        val wormSize = (gameSize.width * WORM_SCREEN_WIDTH_PERCENT).toInt()
        _state.update { it.copy(isGameStarted = true) }
        while (state.value.isGameStarted) {
            delay(DELAY_BETWEEN_WORMS)
            _state.update {
                it.copy(wormOffset = getRandomWormOffset(gameSize, wormSize))
            }
            delay(WORM_DISPLAY_TIME)
            _state.update {
                it.copy(wormOffset = null)
            }
        }
    }


    private fun processClickOnWarm() = viewModelScope.launch {
        _state.update {
            it.copy(wormOffset = null)
        }
    }

    private fun processSetGameSize(size: IntSize) = viewModelScope.launch {
        _state.update { it.copy(gameSize = size) }
    }

    private fun getRandomWormOffset(gameSize: IntSize, wormSize: Int): IntOffset {
        return IntOffset(
            x = (0..(gameSize.width - wormSize)).random(),
            y = (0..(gameSize.height - wormSize)).random(),
        )
    }

    companion object {
        private const val DELAY_BETWEEN_WORMS = 600L
        private const val WORM_DISPLAY_TIME = 800L
        const val REWARD_PER_WORM = 5
        const val WORM_SCREEN_WIDTH_PERCENT = 0.15f
    }
}