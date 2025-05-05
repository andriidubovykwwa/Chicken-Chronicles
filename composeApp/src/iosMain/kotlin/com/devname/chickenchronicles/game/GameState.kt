package com.devname.chickenchronicles.game

import com.devname.chickenchronicles.data.Chicken
import com.devname.chickenchronicles.data.EggBasket
import com.devname.chickenchronicles.utility.currentTimeSeconds

data class GameState(
    val chicken: Chicken = Chicken(),
    val eggBasket: EggBasket = EggBasket(),
    val coins: Int = DEFAULT_COINS,
    val lastUpdateTimeStampSeconds: Long = 0,
    val lastMathQuizTimeStampSeconds: Long = 0
) {
    // TODO: make save in json
    val canUpgradeThirst: Boolean
        get() = coins >= chicken.thirstUpgradePrice

    val canUpgradeHunger: Boolean
        get() = coins >= chicken.hungerUpgradePrice

    val canUpgradeEggBasket: Boolean
        get() = coins >= eggBasket.upgradePrice

    val canTryMathQuiz: Boolean
        get() = currentTimeSeconds() - lastMathQuizTimeStampSeconds >= MATH_QUIZ_INTERVAL

    companion object {
        private const val DEFAULT_COINS = 10000

        // Default value - 1. Change for speed up game for testing
        const val TIME_MULTIPLIER = 500
        const val MATH_QUIZ_INTERVAL = 86400 // Once a day
    }
}
