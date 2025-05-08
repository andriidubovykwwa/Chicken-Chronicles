package com.emirhanok20.chickenchronicles.utility.ui.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.window.Dialog
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.dialog_bg
import chickenchronicles.composeapp.generated.resources.info
import chickenchronicles.composeapp.generated.resources.info_icon
import com.emirhanok20.chickenchronicles.navigation.game.GameState
import com.emirhanok20.chickenchronicles.utility.ui.AppText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun InfoDialog(
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
                Image(
                    modifier = Modifier.height(50.dp),
                    painter = painterResource(Res.drawable.info_icon),
                    contentDescription = stringResource(Res.string.info),
                    contentScale = ContentScale.FillHeight
                )
                AppText(text = "Age: ${state.chicken.timeAliveStr}")
                AppText(text = "Common Eggs Laid: ${state.eggBasket.defaultEggsLaid}")
                AppText(text = "Golden Eggs Laid: ${state.eggBasket.goldenEggsLaid}")
            }
        }
    }
}