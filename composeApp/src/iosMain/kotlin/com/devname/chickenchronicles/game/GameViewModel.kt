package com.devname.chickenchronicles.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devname.chickenchronicles.data.Chicken
import com.devname.chickenchronicles.utility.currentTimeSeconds
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.TimeTick -> processTimeTick(event.seconds)
            is GameEvent.RefillHunger -> processRefillHunger()
            is GameEvent.RefillThirst -> processRefillThirst()
            is GameEvent.StrokeChicken -> processStrokeChicken()
            is GameEvent.UpgradeEggBasket -> processUpgradeEggBasket()
            is GameEvent.UpgradeHunger -> processUpgradeHunger()
            is GameEvent.UpgradeThirst -> processUpgradeThirst()
        }
    }

    private fun processUpgradeEggBasket() = viewModelScope.launch {
        if (!state.value.canUpgradeEggBasket) return@launch
        val eggBasket = state.value.eggBasket
        _state.update {
            it.copy(
                coins = it.coins - eggBasket.upgradePrice,
                eggBasket = eggBasket.copy(lvl = eggBasket.lvl + 1)
            )
        }
    }

    private fun processUpgradeHunger() = viewModelScope.launch {
        if (!state.value.canUpgradeHunger) return@launch
        val chicken = state.value.chicken
        _state.update {
            it.copy(
                coins = it.coins - chicken.hungerUpgradePrice,
                chicken = chicken.copy(hungerLvl = chicken.hungerLvl + 1)
            )
        }
    }

    private fun processUpgradeThirst() = viewModelScope.launch {
        if (!state.value.canUpgradeThirst) return@launch
        val chicken = state.value.chicken
        _state.update {
            it.copy(
                coins = it.coins - chicken.thirstUpgradePrice,
                chicken = chicken.copy(thirstLvl = chicken.thirstLvl + 1)
            )
        }
    }

    private fun processTimeTick(seconds: Int) = viewModelScope.launch {
        _state.update {
            it.copy(
                chicken = it.chicken.getCopyAfterTimePass(seconds),
                eggBasket = it.eggBasket.getCopyAfterTimePass(it.chicken),
                lastUpdateTimeStampSeconds = currentTimeSeconds()
            )
        }
    }

    private fun processRefillHunger() = viewModelScope.launch {
        val chicken = state.value.chicken
        val refillPrice = chicken.hungerRefillPrice
        val coins = state.value.coins
        if (coins < refillPrice) return@launch
        _state.update {
            it.copy(
                coins = coins - refillPrice,
                chicken = chicken.copy(hunger = chicken.maxHunger.toFloat())
            )
        }
    }

    private fun processRefillThirst() = viewModelScope.launch {
        val chicken = state.value.chicken
        val refillPrice = chicken.thirstRefillPrice
        val coins = state.value.coins
        if (coins < refillPrice) return@launch
        _state.update {
            it.copy(
                coins = coins - refillPrice,
                chicken = chicken.copy(thirst = chicken.maxThirst.toFloat())
            )
        }
    }

    private fun processStrokeChicken() = viewModelScope.launch {
        val chicken = state.value.chicken
        _state.update {
            it.copy(
                chicken = chicken.copy(
                    happiness = (chicken.happiness + Chicken.HAPPINESS_REFILL_PER_STROKE).coerceAtMost(
                        chicken.maxHappiness.toFloat()
                    )
                )
            )
        }
    }
}