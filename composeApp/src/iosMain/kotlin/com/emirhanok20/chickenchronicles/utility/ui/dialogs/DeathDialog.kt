package com.emirhanok20.chickenchronicles.utility.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import chickenchronicles.composeapp.generated.resources.dialog_bg
import com.emirhanok20.chickenchronicles.utility.ui.AppButton
import com.emirhanok20.chickenchronicles.utility.ui.AppText
import org.jetbrains.compose.resources.painterResource

@Composable
fun DeathDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
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
                AppText(
                    text = "Your chicken is dead. Start with new chicken",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
                AppButton(onClick = { onDismiss() }) {
                    AppText(text = "OK")
                }
            }
        }
    }
}