package com.emirhanok20.chickenchronicles.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.Start -> processStart()
        }
    }

    private fun processStart() = viewModelScope.launch {
        delay(LOADING_SCREEN_SHOW_MILLIS)
        _state.update { it.copy(currentScreen = Screen.GAME) }
    }

    companion object {
        const val LOADING_SCREEN_SHOW_MILLIS = 1500L
    }
}