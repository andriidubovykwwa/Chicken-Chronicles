package com.emirhanok20.chickenchronicles.navigation.game

import com.emirhanok20.chickenchronicles.data.Chicken
import com.emirhanok20.chickenchronicles.data.EggBasket
import com.emirhanok20.chickenchronicles.utility.currentTimeSeconds
import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    val chicken: Chicken = Chicken(),
    val eggBasket: EggBasket = EggBasket(),
    val coins: Int = DEFAULT_COINS,
    val lastUpdateTimeStampSeconds: Long = 0,
    val lastMathQuizTimeStampSeconds: Long = 0
) {
    val canUpgradeThirst: Boolean
        get() = coins >= chicken.thirstUpgradePrice

    val canUpgradeHunger: Boolean
        get() = coins >= chicken.hungerUpgradePrice

    val canUpgradeEggBasket: Boolean
        get() = coins >= eggBasket.upgradePrice

    val canRefillThirst: Boolean
        get() = coins >= chicken.thirstRefillPrice

    val canRefillHunger: Boolean
        get() = coins >= chicken.hungerRefillPrice

    val canTryMathQuiz: Boolean
        get() = secondsToMathQuiz <= 0

    val secondsToMathQuiz: Int
        get() {
            val quizTime =
                lastMathQuizTimeStampSeconds + (MATH_QUIZ_INTERVAL / TIME_MULTIPLIER.toFloat()).toInt()
            return (quizTime - currentTimeSeconds()).coerceAtLeast(0).toInt()
        }

    val canSellEggs: Boolean
        get() = eggBasket.eggs.isNotEmpty()

    companion object {
        private const val DEFAULT_COINS = 100

        // Default value - 1. Change for speed up game for testing
        const val TIME_MULTIPLIER = 1
        const val MATH_QUIZ_INTERVAL = 86400 // Once a day
        const val AUTO_SAVE_MILLIS = 60000L
    }
}
