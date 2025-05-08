package com.emirhanok20.chickenchronicles.navigation.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.basket
import chickenchronicles.composeapp.generated.resources.bg
import chickenchronicles.composeapp.generated.resources.help
import chickenchronicles.composeapp.generated.resources.help_icon
import chickenchronicles.composeapp.generated.resources.info
import chickenchronicles.composeapp.generated.resources.info_icon
import chickenchronicles.composeapp.generated.resources.mini_game
import chickenchronicles.composeapp.generated.resources.quiz
import chickenchronicles.composeapp.generated.resources.quiz_icon
import chickenchronicles.composeapp.generated.resources.worm
import com.emirhanok20.chickenchronicles.utility.OrientationController
import com.emirhanok20.chickenchronicles.utility.ui.ChickenComponent
import com.emirhanok20.chickenchronicles.utility.ui.ChickenStats
import com.emirhanok20.chickenchronicles.utility.ui.CoinsDisplay
import com.emirhanok20.chickenchronicles.utility.ui.EnergyDisplay
import com.emirhanok20.chickenchronicles.utility.ui.dialogs.DeathDialog
import com.emirhanok20.chickenchronicles.utility.ui.dialogs.EggBasketDialog
import com.emirhanok20.chickenchronicles.utility.ui.dialogs.HappinessDialog
import com.emirhanok20.chickenchronicles.utility.ui.dialogs.HealthDialog
import com.emirhanok20.chickenchronicles.utility.ui.dialogs.HelpDialog
import com.emirhanok20.chickenchronicles.utility.ui.dialogs.HungerDialog
import com.emirhanok20.chickenchronicles.utility.ui.dialogs.InfoDialog
import com.emirhanok20.chickenchronicles.utility.ui.dialogs.ThirstDialog
import com.emirhanok20.chickenchronicles.utility.ui.math_quiz.MathQuizDialog
import com.emirhanok20.chickenchronicles.utility.ui.mini_game.MiniGameScreen
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(viewModel: GameViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val onEvent = viewModel::onEvent

    var isHealthDialogOpened by remember { mutableStateOf(false) }
    var isHungerDialogOpened by remember { mutableStateOf(false) }
    var isThirstDialogOpened by remember { mutableStateOf(false) }
    var isHappinessDialogOpened by remember { mutableStateOf(false) }
    var isEggBasketDialogOpened by remember { mutableStateOf(false) }
    var isInfoOpened by remember { mutableStateOf(false) }
    var isHelpOpened by remember { mutableStateOf(false) }
    var isMathQuizOpened by remember { mutableStateOf(false) }
    var isMiniGameOpened by remember { mutableStateOf(false) }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                onEvent(GameEvent.SaveProgress)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(Unit) {
        OrientationController().orientation = OrientationController.Orientation.PORTRAIT
        while (true) {
            delay(1000)
            onEvent(GameEvent.TimeTick(1))
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(GameState.AUTO_SAVE_MILLIS)
            onEvent(GameEvent.SaveProgress)
        }
    }

    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.bg),
            contentScale = ContentScale.FillBounds
        )
    ) {
        if (!isMiniGameOpened) {
            Box(Modifier.fillMaxSize().safeContentPadding()) {
                Column(
                    Modifier.align(Alignment.TopStart),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    EnergyDisplay(
                        energy = state.chicken.energy,
                        maxEnergy = state.chicken.maxEnergy
                    )
                    CoinsDisplay(coins = state.coins)
                }
                ChickenComponent(
                    Modifier.align(Alignment.Center).fillMaxWidth(0.75f),
                    chicken = state.chicken,
                    onClick = { onEvent(GameEvent.StrokeChicken) }
                )
                Column(
                    Modifier.align(Alignment.TopEnd),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Image(
                        modifier = Modifier.width(50.dp).clip(RoundedCornerShape(10))
                            .clickable { isEggBasketDialogOpened = true },
                        painter = painterResource(Res.drawable.basket),
                        contentDescription = stringResource(Res.string.basket),
                        contentScale = ContentScale.FillWidth
                    )
                    Image(
                        modifier = Modifier.width(50.dp).clip(RoundedCornerShape(10))
                            .clickable { isMathQuizOpened = true },
                        painter = painterResource(Res.drawable.quiz_icon),
                        contentDescription = stringResource(Res.string.quiz),
                        contentScale = ContentScale.FillWidth
                    )
                    Image(
                        modifier = Modifier.width(50.dp).clip(RoundedCornerShape(10))
                            .clickable { isMiniGameOpened = true },
                        painter = painterResource(Res.drawable.worm),
                        contentDescription = stringResource(Res.string.mini_game),
                        contentScale = ContentScale.FillWidth
                    )
                    Image(
                        modifier = Modifier.width(50.dp).clip(RoundedCornerShape(10))
                            .clickable { isInfoOpened = true },
                        painter = painterResource(Res.drawable.info_icon),
                        contentDescription = stringResource(Res.string.info),
                        contentScale = ContentScale.FillWidth
                    )
                    Image(
                        modifier = Modifier.width(50.dp).clip(RoundedCornerShape(10))
                            .clickable { isHelpOpened = true },
                        painter = painterResource(Res.drawable.help_icon),
                        contentDescription = stringResource(Res.string.help),
                        contentScale = ContentScale.FillWidth
                    )
                }
                ChickenStats(
                    Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
                    onHealthClick = { isHealthDialogOpened = true },
                    onHungerClick = { isHungerDialogOpened = true },
                    onThirstClick = { isThirstDialogOpened = true },
                    onHappinessClick = { isHappinessDialogOpened = true },
                    chicken = state.chicken,
                )
            }
        } else {
            MiniGameScreen(
                onCloseWindow = { isMiniGameOpened = false },
                onGameEvent = onEvent,
                gameState = state
            )
        }
    }
    if (state.chicken.isDead) {
        DeathDialog(onDismiss = { onEvent(GameEvent.Restart) })
    } else if (isHealthDialogOpened) {
        HealthDialog(
            state = state,
            onDismiss = { isHealthDialogOpened = false }
        )
    } else if (isThirstDialogOpened) {
        ThirstDialog(
            state = state,
            onEvent = onEvent,
            onDismiss = { isThirstDialogOpened = false }
        )
    } else if (isHungerDialogOpened) {
        HungerDialog(
            state = state,
            onEvent = onEvent,
            onDismiss = { isHungerDialogOpened = false }
        )
    } else if (isHappinessDialogOpened) {
        HappinessDialog(
            state = state,
            onDismiss = { isHappinessDialogOpened = false }
        )
    } else if (isEggBasketDialogOpened) {
        EggBasketDialog(
            state = state,
            onEvent = onEvent,
            onDismiss = { isEggBasketDialogOpened = false }
        )
    } else if (isInfoOpened) {
        InfoDialog(
            state = state,
            onDismiss = { isInfoOpened = false }
        )
    } else if (isHelpOpened) {
        HelpDialog(onDismiss = { isHelpOpened = false })
    } else if (isMathQuizOpened) {
        MathQuizDialog(
            gameState = state,
            onGameEvent = onEvent,
            onDismissDialog = { isMathQuizOpened = false }
        )
    }
}