package com.emirhanok20.chickenchronicles.utility.ui.mini_game

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.arrow
import chickenchronicles.composeapp.generated.resources.back
import chickenchronicles.composeapp.generated.resources.bg
import chickenchronicles.composeapp.generated.resources.worm
import com.emirhanok20.chickenchronicles.navigation.game.GameEvent
import com.emirhanok20.chickenchronicles.navigation.game.GameState
import com.emirhanok20.chickenchronicles.utility.ui.AppButton
import com.emirhanok20.chickenchronicles.utility.ui.AppText
import com.emirhanok20.chickenchronicles.utility.ui.CoinsDisplay
import com.emirhanok20.chickenchronicles.utility.ui.EnergyDisplay
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MiniGameScreen(
    onGameEvent: (GameEvent) -> Unit,
    onCloseWindow: () -> Unit,
    gameState: GameState,
    viewModel: MiniGameViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val onEvent = viewModel::onEvent
    fun onClose() {
        onEvent(MiniGameEvent.Reset)
        onCloseWindow()
    }

    LaunchedEffect(state.isGameStarted) {
        while (state.isGameStarted) {
            delay(1000L)
            onGameEvent(GameEvent.TakeEnergy(1))
        }
    }

    LaunchedEffect(gameState.chicken.energy) {
        if (gameState.chicken.energy < 1f) onEvent(MiniGameEvent.EndGame)
    }

    Column(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.bg),
            contentScale = ContentScale.FillBounds
        ).safeContentPadding()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.height(50.dp).clip(RoundedCornerShape(25)).clickable {
                    onClose()
                },
                painter = painterResource(Res.drawable.arrow),
                contentDescription = stringResource(Res.string.back),
                contentScale = ContentScale.FillHeight
            )
            EnergyDisplay(energy = gameState.chicken.energy)
            CoinsDisplay(coins = gameState.coins)
        }
        Box(Modifier.fillMaxSize().onSizeChanged { onEvent(MiniGameEvent.SetGameSize(it)) }) {
            if (state.isGameEnded) {
                Column(
                    Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AppText(text = "You have no energy")
                }
            } else if (!state.isGameStarted) {
                Column(
                    Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AppButton(onClick = { onEvent(MiniGameEvent.Start) }) {
                        AppText(text = "Start")
                    }
                }
            }
            state.wormOffset?.let {
                Image(
                    modifier = Modifier.align(Alignment.TopStart)
                        .fillMaxSize(MiniGameViewModel.WORM_SCREEN_WIDTH_PERCENT).aspectRatio(1f)
                        .offset { it }
                        .clickable {
                            onGameEvent(GameEvent.AddCoins(MiniGameViewModel.REWARD_PER_WORM))
                            onEvent(MiniGameEvent.ClickOnWarm)
                        },
                    painter = painterResource(Res.drawable.worm),
                    contentDescription = stringResource(Res.string.worm),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}