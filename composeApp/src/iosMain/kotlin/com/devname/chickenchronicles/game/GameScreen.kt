package com.devname.chickenchronicles.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.devname.chickenchronicles.utility.OrientationController
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(viewModel: GameViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val onEvent = viewModel::onEvent

    LaunchedEffect(Unit) {
        OrientationController().orientation = OrientationController.Orientation.PORTRAIT
        while (true) {
            delay(1000)
            onEvent(GameEvent.TimeTick(1))
        }
    }

    Box(Modifier.fillMaxSize()) {
        Text(text = state.toString(), modifier = Modifier.align(Alignment.Center))
        Column(Modifier.align(Alignment.BottomCenter)) {
            Button(onClick = { onEvent(GameEvent.RefillThirst) }) {
                Text("Refill thirst(${state.chicken.thirstRefillPrice})")
            }
            Button(onClick = { onEvent(GameEvent.UpgradeThirst) }) {
                Text("Upgrade thirst(${state.chicken.thirstUpgradePrice})")
            }
            Button(onClick = { onEvent(GameEvent.RefillHunger) }) {
                Text("Refill hunger(${state.chicken.hungerRefillPrice})")
            }
            Button(onClick = { onEvent(GameEvent.UpgradeHunger) }) {
                Text("Upgrade hunger(${state.chicken.hungerUpgradePrice})")
            }
            Button(onClick = { onEvent(GameEvent.UpgradeEggBasket) }) {
                Text("Upgrade egg basket(${state.eggBasket.upgradePrice})")
            }
            Button(onClick = { onEvent(GameEvent.StrokeChicken) }) {
                Text("Stroke")
            }
        }
    }
}