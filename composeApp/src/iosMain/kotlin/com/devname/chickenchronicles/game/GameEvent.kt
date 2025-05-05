package com.devname.chickenchronicles.game

sealed interface GameEvent {
    data class TimeTick(val seconds: Int) : GameEvent
    data object StrokeChicken : GameEvent
    data object RefillHunger : GameEvent
    data object RefillThirst : GameEvent
    data object UpgradeEggBasket : GameEvent
    data object UpgradeHunger : GameEvent
    data object UpgradeThirst : GameEvent
}