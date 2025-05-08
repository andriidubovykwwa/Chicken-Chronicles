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
import chickenchronicles.composeapp.generated.resources.energy
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EnergyDisplay(
    modifier: Modifier = Modifier,
    energy: Float,
    maxEnergy: Int? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.height(45.dp),
            painter = painterResource(Res.drawable.energy),
            contentDescription = stringResource(Res.string.energy),
            contentScale = ContentScale.FillHeight
        )
        AppText(
            text = "${energy.toInt()}${if (maxEnergy != null) "/$maxEnergy" else ""}",
            fontSize = 25.sp
        )
    }
}