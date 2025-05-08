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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.clock
import chickenchronicles.composeapp.generated.resources.dialog_bg
import chickenchronicles.composeapp.generated.resources.happiness
import chickenchronicles.composeapp.generated.resources.happiness_icon
import chickenchronicles.composeapp.generated.resources.happiness_upgrade_text
import chickenchronicles.composeapp.generated.resources.time
import com.emirhanok20.chickenchronicles.navigation.game.GameState
import com.emirhanok20.chickenchronicles.utility.secondsToTimeStr
import com.emirhanok20.chickenchronicles.utility.ui.AppText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import kotlin.math.roundToInt

@Composable
fun HappinessDialog(
    modifier: Modifier = Modifier,
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
                        painter = painterResource(Res.drawable.happiness_icon),
                        contentDescription = stringResource(Res.string.happiness),
                        contentScale = ContentScale.FillHeight
                    )
                    AppText(text = "${state.chicken.happiness.roundToInt()}/${state.chicken.maxHappiness}")
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
                    AppText(text = state.chicken.happinessSecondsLeft.secondsToTimeStr())
                }
                AppText(
                    text = stringResource(Res.string.happiness_upgrade_text),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}