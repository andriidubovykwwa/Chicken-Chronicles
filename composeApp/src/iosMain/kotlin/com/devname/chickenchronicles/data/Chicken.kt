package com.devname.chickenchronicles.data

import com.devname.chickenchronicles.game.GameState
import kotlin.math.ceil
import kotlin.math.roundToInt

data class Chicken(
    val timeAlive: Int = 0, // Age in seconds
    val health: Float = DEFAULT_MAX_HEALTH.toFloat(),
    val thirst: Float = DEFAULT_MAX_THIRST.toFloat(),
    val hunger: Float = DEFAULT_MAX_HUNGER.toFloat(),
    val happiness: Float = DEFAULT_MAX_HAPPINESS.toFloat(),
    val energy: Float = DEFAULT_MAX_ENERGY.toFloat(),
    val thirstLvl: Int = 1,
    val hungerLvl: Int = 1
) {
    val timeAliveInDays: Int // Real time (real day - in game month)
        get() = timeAlive / (60 * 60 * 24)

    val maxHealth: Int
        get() = DEFAULT_MAX_HEALTH + timeAliveInDays * MAX_HEALTH_UPGRADE_PER_DAY

    val maxEnergy: Int
        get() = DEFAULT_MAX_ENERGY + timeAliveInDays * MAX_ENERGY_UPGRADE_PER_DAY

    val maxHappiness: Int
        get() = DEFAULT_MAX_HAPPINESS + timeAliveInDays * MAX_HAPPINESS_UPGRADE_PER_DAY

    val maxThirst: Int
        get() = DEFAULT_MAX_THIRST + (thirstLvl - 1) * MAX_THIRST_UPGRADE_PER_THIRST_LVL

    val maxHunger: Int
        get() = DEFAULT_MAX_HUNGER + (hungerLvl - 1) * MAX_HUNGER_UPGRADE_PER_HUNGER_LVL

    val isDead: Boolean
        get() = health <= 0f

    val thirstRefillPrice: Int
        get() = ceil((maxThirst - thirst) * THIRST_REFILL_PRICE_PER_UNIT).toInt()

    val hungerRefillPrice: Int
        get() = ceil((maxHunger - hunger) * HUNGER_REFILL_PRICE_PER_UNIT).toInt()

    val thirstPercent: Int
        get() = ((thirst / maxThirst) * 100).roundToInt()

    val hungerPercent: Int
        get() = ((hunger / maxHunger) * 100).roundToInt()

    val happinessPercent: Int
        get() = ((happiness / maxHappiness) * 100).roundToInt()

    val thirstUpgradePrice: Int
        get() = DEFAULT_THIRST_UPGRADE_PRICE + THIRST_UPGRADE_PRICE_INCREASE_PER_LVL * (thirstLvl - 1)

    val hungerUpgradePrice: Int
        get() = DEFAULT_HUNGER_UPGRADE_PRICE + HUNGER_UPGRADE_PRICE_INCREASE_PER_LVL * (hungerLvl - 1)

    val happinessSecondsLeft: Int
        get() = if (HAPPINESS_DRAIN_RATE > 0f) (happiness / HAPPINESS_DRAIN_RATE).toInt() else 99999999

    val hungerSecondsLeft: Int
        get() = if (HUNGER_DRAIN_RATE > 0f) (hunger / HUNGER_DRAIN_RATE).toInt() else 99999999

    val thirstSecondsLeft: Int
        get() = if (THIRST_DRAIN_RATE > 0f) (thirst / THIRST_DRAIN_RATE).toInt() else 99999999

    val healthSecondsLeft: Int
        get() = if (HEALTH_DRAIN_RATE > 0f) (health / HEALTH_DRAIN_RATE).toInt() else 99999999

    fun getCopyAfterTimePass(secondsPassed: Int): Chicken {
        val seconds = secondsPassed * GameState.TIME_MULTIPLIER
        fun timeToZero(value: Float, drainRate: Float): Int {
            return if (drainRate > 0f) (value / drainRate).toInt()
                .coerceAtMost(seconds) else seconds
        }

        val thirstZeroTime = timeToZero(thirst, THIRST_DRAIN_RATE)
        val hungerZeroTime = timeToZero(hunger, HUNGER_DRAIN_RATE)
        val happinessZeroTime = timeToZero(happiness, HAPPINESS_DRAIN_RATE)

        val thirstDrain = seconds * THIRST_DRAIN_RATE
        val hungerDrain = seconds * HUNGER_DRAIN_RATE
        val happinessDrain = seconds * HAPPINESS_DRAIN_RATE
        val energyRestoration = seconds * ENERGY_RESTORATION_RATE

        val newThirst = (thirst - thirstDrain).coerceAtLeast(0f)
        val newHunger = (hunger - hungerDrain).coerceAtLeast(0f)
        val newHappiness = (happiness - happinessDrain).coerceAtLeast(0f)
        val newEnergy = (energy + energyRestoration).coerceAtMost(maxEnergy.toFloat())

        // Time when health drains
        val healthDrainTime = seconds - minOf(thirstZeroTime, hungerZeroTime, happinessZeroTime)
        val healthChange =
            health + (if (healthDrainTime > 0) -healthDrainTime * HEALTH_DRAIN_RATE else seconds * HEALTH_DRAIN_RATE)

        val newHealth = healthChange.coerceIn(0f, maxHealth.toFloat())

        return copy(
            timeAlive = timeAlive + seconds,
            thirst = newThirst.coerceAtMost(maxThirst.toFloat()),
            hunger = newHunger.coerceAtMost(maxHunger.toFloat()),
            happiness = newHappiness.coerceAtMost(maxHappiness.toFloat()),
            health = newHealth,
            energy = newEnergy
        )
    }

    fun getGoldenEggChanceMultiplier(): Float {
        val ageMultiplier = if (timeAliveInDays >= 12) {
            2f
        } else if (timeAliveInDays >= 9f) {
            1.75f
        } else if (timeAliveInDays >= 6f) {
            1.5f
        } else if (timeAliveInDays >= 2f) {
            1.25f
        } else 1f
        val happinessMultiplier = if (happinessPercent >= 75f) {
            1.5f
        } else if (happinessPercent >= 50f) {
            1.25f
        } else 1f
        val hungerMultiplier = if (hungerPercent >= 75f) {
            1.5f
        } else if (hungerPercent >= 50f) {
            1.25f
        } else 1f
        val thirstMultiplier = if (thirstPercent >= 75f) {
            1.5f
        } else if (thirstPercent >= 50f) {
            1.25f
        } else 1f
        return ageMultiplier * happinessMultiplier * hungerMultiplier * thirstMultiplier
    }


    companion object {
        private const val DEFAULT_MAX_HEALTH = 100
        private const val DEFAULT_MAX_THIRST = 100
        private const val DEFAULT_MAX_HUNGER = 100
        private const val DEFAULT_MAX_HAPPINESS = 100
        private const val DEFAULT_MAX_ENERGY = 100

        private const val MAX_HEALTH_UPGRADE_PER_DAY = 5
        private const val MAX_ENERGY_UPGRADE_PER_DAY = 10
        private const val MAX_HAPPINESS_UPGRADE_PER_DAY = 15
        private const val MAX_THIRST_UPGRADE_PER_THIRST_LVL = 20
        private const val MAX_HUNGER_UPGRADE_PER_HUNGER_LVL = 20

        private const val ENERGY_RESTORATION_RATE = 0.002f // 100f for ~ 14 hours

        // Drain rate per second
        private const val THIRST_DRAIN_RATE = 0.002f // 100f for ~ 14 hours
        private const val HUNGER_DRAIN_RATE = 0.0015f // 100f for ~ 18.5 hours
        private const val HAPPINESS_DRAIN_RATE = 0.001f // 100f for ~ 28 hours

        /* Health drained only if thirst, hunger, or happiness reach 0
        If thirst, hunger and happiness greater than 0 that heath restoring with same rate*/
        private const val HEALTH_DRAIN_RATE = 0.0025f

        private const val THIRST_REFILL_PRICE_PER_UNIT = 0.25f
        private const val HUNGER_REFILL_PRICE_PER_UNIT = 0.25f

        private const val DEFAULT_THIRST_UPGRADE_PRICE = 50
        private const val DEFAULT_HUNGER_UPGRADE_PRICE = 50
        private const val THIRST_UPGRADE_PRICE_INCREASE_PER_LVL = 25
        private const val HUNGER_UPGRADE_PRICE_INCREASE_PER_LVL = 25


        const val HAPPINESS_REFILL_PER_STROKE = 5f
    }
}