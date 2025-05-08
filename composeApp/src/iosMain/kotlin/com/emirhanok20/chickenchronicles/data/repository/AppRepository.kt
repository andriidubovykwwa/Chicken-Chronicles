package com.emirhanok20.chickenchronicles.data.repository

import com.emirhanok20.chickenchronicles.navigation.game.GameState

interface AppRepository {
    fun saveState(state: GameState)
    fun getSavedState(): GameState
}