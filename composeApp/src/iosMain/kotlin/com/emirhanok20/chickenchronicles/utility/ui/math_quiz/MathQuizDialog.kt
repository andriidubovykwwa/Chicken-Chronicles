package com.emirhanok20.chickenchronicles.utility.ui.math_quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.clock
import chickenchronicles.composeapp.generated.resources.dialog_bg
import chickenchronicles.composeapp.generated.resources.eras_itc_bold
import chickenchronicles.composeapp.generated.resources.quiz
import chickenchronicles.composeapp.generated.resources.quiz_icon
import chickenchronicles.composeapp.generated.resources.time
import com.emirhanok20.chickenchronicles.navigation.game.GameEvent
import com.emirhanok20.chickenchronicles.navigation.game.GameState
import com.emirhanok20.chickenchronicles.utility.secondsToTimeStr
import com.emirhanok20.chickenchronicles.utility.ui.AppButton
import com.emirhanok20.chickenchronicles.utility.ui.AppText
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MathQuizDialog(
    modifier: Modifier = Modifier,
    onGameEvent: (GameEvent) -> Unit,
    onDismissDialog: () -> Unit,
    gameState: GameState,
    viewModel: MathQuizViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val onEvent = viewModel::onEvent
    fun onDismiss() {
        onEvent(MathQuizEvent.Reset)
        onDismissDialog()
    }

    LaunchedEffect(state.isQuizStarted) {
        while (state.isQuizStarted) {
            delay(1000)
            onEvent(MathQuizEvent.SecondTick)
        }
    }
    LaunchedEffect(state.winStatus) {
        if (state.winStatus != null) {
            onGameEvent(GameEvent.EndMathQuiz(state.winStatus ?: false))
        }
    }

    Dialog(onDismissRequest = {
        if (state.winStatus != null || !state.isQuizStarted) onDismiss()
    }) {
        Box(
            modifier.size(300.dp, 348.dp).paint(
                painter = painterResource(Res.drawable.dialog_bg),
                contentScale = ContentScale.FillBounds
            ).padding(vertical = 20.dp, horizontal = 10.dp)
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                if (state.isQuizStarted) {
                    when (state.winStatus) {
                        true -> {
                            AppText(
                                text = "You win ${MathQuizState.DEFAULT_REWARD} coins",
                                textAlign = TextAlign.Center
                            )
                            AppButton(onClick = { onDismiss() }) {
                                AppText(text = "OK")
                            }
                        }

                        false -> {
                            AppText(
                                text = "You lose. You can try again later",
                                textAlign = TextAlign.Center
                            )
                            AppButton(onClick = { onDismiss() }) {
                                AppText(text = "OK")
                            }
                        }

                        else -> {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier.height(50.dp),
                                    painter = painterResource(Res.drawable.quiz_icon),
                                    contentDescription = stringResource(Res.string.quiz),
                                    contentScale = ContentScale.FillHeight
                                )
                                AppText(text = (state.expressionsLeft + 1).toString())
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier.height(50.dp),
                                    painter = painterResource(Res.drawable.clock),
                                    contentDescription = stringResource(Res.string.time),
                                    contentScale = ContentScale.FillHeight
                                )
                                AppText(text = state.secondsLeft.toString())
                            }
                            AppText(
                                text = state.currentExpression?.displayString.toString(),
                                fontSize = 30.sp,
                            )
                            TextField(
                                modifier = Modifier.width(150.dp),
                                value = state.userAnswer,
                                onValueChange = {
                                    if (it.all { c -> c.isDigit() }) {
                                        onEvent(MathQuizEvent.UpdateUserAnswer(it))
                                    }
                                },
                                textStyle = TextStyle(
                                    fontSize = 30.sp,
                                    fontFamily = FontFamily(Font(Res.font.eras_itc_bold))
                                ),
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = Color.White,
                                    focusedIndicatorColor = Color.Green,
                                    cursorColor = Color.Green
                                ),
                                placeholder = {
                                    AppText(text = "Answer...", fontSize = 20.sp)
                                },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                            )
                            AppButton(onClick = { onEvent(MathQuizEvent.SubmitAnswer) }) {
                                AppText(text = "Submit")
                            }
                        }
                    }
                } else if (gameState.canTryMathQuiz) {
                    AppButton(onClick = { onEvent(MathQuizEvent.StartQuiz) }) {
                        AppText(text = "Start")
                    }
                } else {
                    AppText(text = "Available In: ${gameState.secondsToMathQuiz.secondsToTimeStr()}")
                }
            }
        }
    }
}