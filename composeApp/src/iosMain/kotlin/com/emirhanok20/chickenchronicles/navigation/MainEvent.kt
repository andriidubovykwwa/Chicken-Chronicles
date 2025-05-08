package com.emirhanok20.chickenchronicles.navigation

sealed interface MainEvent {
    data object Start : MainEvent
}