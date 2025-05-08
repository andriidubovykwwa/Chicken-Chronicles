package com.emirhanok20.chickenchronicles.utility.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.coin
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CoinsDisplay(
    modifier: Modifier = Modifier,
    coins: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.height(45.dp),
            painter = painterResource(Res.drawable.coin),
            contentDescription = stringResource(Res.string.coin),
            contentScale = ContentScale.FillHeight
        )
        AppText(text = coins.toString(), fontSize = 25.sp)
    }
}