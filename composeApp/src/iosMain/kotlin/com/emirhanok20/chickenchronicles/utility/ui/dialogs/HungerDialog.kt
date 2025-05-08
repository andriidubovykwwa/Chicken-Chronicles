package com.emirhanok20.chickenchronicles.utility.ui.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.arrow_up
import chickenchronicles.composeapp.generated.resources.clock
import chickenchronicles.composeapp.generated.resources.coin
import chickenchronicles.composeapp.generated.resources.dialog_bg
import chickenchronicles.composeapp.generated.resources.hunger
import chickenchronicles.composeapp.generated.resources.hunger_icon
import chickenchronicles.composeapp.generated.resources.refill
import chickenchronicles.composeapp.generated.resources.time
import chickenchronicles.composeapp.generated.resources.upgrade
import com.emirhanok20.chickenchronicles.navigation.game.GameEvent
import com.emirhanok20.chickenchronicles.navigation.game.GameState
import com.emirhanok20.chickenchronicles.utility.secondsToTimeStr
import com.emirhanok20.chickenchronicles.utility.ui.AppButton
import com.emirhanok20.chickenchronicles.utility.ui.AppText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.roundToInt

@Composable
fun HungerDialog(
    modifier: Modifier = Modifier,
    onEvent: (GameEvent) -> Unit,
    onDismiss: () -> Unit,
    state: GameState,
) {
    Dialog(onDismissRequest = onDismiss) {
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.height(50.dp),
                        painter = painterResource(Res.drawable.hunger_icon),
                        contentDescription = stringResource(Res.string.hunger),
                        contentScale = ContentScale.FillHeight
                    )
                    AppText(text = "${state.chicken.hunger.roundToInt()}/${state.chicken.maxHunger}")
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
                    AppText(text = state.chicken.hungerSecondsLeft.secondsToTimeStr())
                }
                AppButton(
                    enabled = state.canRefillHunger,
                    onClick = { onEvent(GameEvent.RefillHunger) }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppText(text = stringResource(Res.string.refill), fontSize = 25.sp)
                        Image(
                            modifier = Modifier.height(45.dp),
                            painter = painterResource(Res.drawable.coin),
                            contentDescription = stringResource(Res.string.coin),
                            contentScale = ContentScale.FillHeight
                        )
                        AppText(text = state.chicken.hungerRefillPrice.toString(), fontSize = 25.sp)
                    }
                }
                AppButton(
                    enabled = state.canUpgradeHunger,
                    onClick = { onEvent(GameEvent.UpgradeHunger) }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.height(45.dp),
                            painter = painterResource(Res.drawable.arrow_up),
                            contentDescription = stringResource(Res.string.upgrade),
                            contentScale = ContentScale.FillHeight
                        )
                        Image(
                            modifier = Modifier.height(45.dp),
                            painter = painterResource(Res.drawable.coin),
                            contentDescription = stringResource(Res.string.coin),
                            contentScale = ContentScale.FillHeight
                        )
                        AppText(
                            text = state.chicken.hungerUpgradePrice.toString(),
                            fontSize = 25.sp
                        )
                    }
                }
            }
        }
    }
}