package com.emirhanok20.chickenchronicles.navigation

data class MainState(
    val currentScreen: Screen = Screen.LOADING,
)

enum class Screen {
    LOADING, GAME
}
