package com.emirhanok20.chickenchronicles.navigation.game

sealed interface GameEvent {
    data class TimeTick(val seconds: Int) : GameEvent
    data object StrokeChicken : GameEvent
    data object RefillHunger : GameEvent
    data object RefillThirst : GameEvent
    data object UpgradeEggBasket : GameEvent
    data object UpgradeHunger : GameEvent
    data object UpgradeThirst : GameEvent
    data object SellEggs : GameEvent
    data object SaveProgress : GameEvent
    data object Restart : GameEvent
    data class TakeEnergy(val energy: Int) : GameEvent
    data class AddCoins(val coins: Int) : GameEvent
    data class EndMathQuiz(val isWin: Boolean) : GameEvent
}