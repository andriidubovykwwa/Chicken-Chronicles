package com.emirhanok20.chickenchronicles.data

import com.emirhanok20.chickenchronicles.navigation.game.GameState
import com.emirhanok20.chickenchronicles.utility.currentTimeSeconds
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class EggBasket(
    val eggs: List<Egg> = emptyList(),
    val lvl: Int = 1,
    val defaultEggsLaid: Int = 0,
    val goldenEggsLaid: Int = 0,
    val lastEggTimeStampSeconds: Long = 0
) {
    val capacity: Int
        get() = DEFAULT_CAPACITY + (lvl - 1) * CAPACITY_PER_UPGRADE

    val upgradePrice: Int
        get() = DEFAULT_UPGRADE_PRICE + UPGRADE_PRICE_INCREASE_PER_LVL * (lvl - 1)

    val sellPrice: Int
        get() = eggs.sumOf { it.price }

    fun getCopyAfterTimePass(chicken: Chicken): EggBasket {
        val currentSeconds = currentTimeSeconds()
        val secondsPassed = currentSeconds - lastEggTimeStampSeconds
        val seconds = secondsPassed * GameState.TIME_MULTIPLIER
        val multiplier = chicken.getGoldenEggChanceMultiplier()
        val goldenEggChance = DEFAULT_GOLDEN_EGG_CHANCE * multiplier
        if (seconds < DEFAULT_EGG_LAYING_TIME_SECONDS) return this // Not enough time
        val egg = if (Random.nextFloat() <= goldenEggChance) Egg.GOLDEN
        else Egg.DEFAULT
        return if (eggs.size < capacity) {
            copy(
                eggs = eggs + egg,
                defaultEggsLaid = if (egg == Egg.DEFAULT) defaultEggsLaid + 1 else defaultEggsLaid,
                goldenEggsLaid = if (egg == Egg.GOLDEN) goldenEggsLaid + 1 else goldenEggsLaid,
                lastEggTimeStampSeconds = currentSeconds,
            )
        } else {
            copy(
                lastEggTimeStampSeconds = currentSeconds,
            )
        }
    }

    companion object {
        private const val DEFAULT_CAPACITY = 4
        private const val DEFAULT_UPGRADE_PRICE = 50
        private const val UPGRADE_PRICE_INCREASE_PER_LVL = 100
        private const val CAPACITY_PER_UPGRADE = 2
        private const val DEFAULT_GOLDEN_EGG_CHANCE = 0.05f
        private const val DEFAULT_EGG_LAYING_TIME_SECONDS = 1 // 7200 seconds - 2 hours
    }
}