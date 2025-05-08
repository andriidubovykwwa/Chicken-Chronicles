package com.emirhanok20.chickenchronicles.utility.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.happiness
import chickenchronicles.composeapp.generated.resources.happiness_icon
import chickenchronicles.composeapp.generated.resources.health
import chickenchronicles.composeapp.generated.resources.health_icon
import chickenchronicles.composeapp.generated.resources.hunger
import chickenchronicles.composeapp.generated.resources.hunger_icon
import chickenchronicles.composeapp.generated.resources.thirst
import chickenchronicles.composeapp.generated.resources.thirst_icon
import com.emirhanok20.chickenchronicles.data.Chicken
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChickenStats(
    modifier: Modifier = Modifier,
    onHealthClick: () -> Unit,
    onThirstClick: () -> Unit,
    onHungerClick: () -> Unit,
    onHappinessClick: () -> Unit,
    chicken: Chicken,
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StatItem(
            Modifier.weight(1f).aspectRatio(1f),
            onClick = onHealthClick,
            percent = chicken.healthPercent,
            description = stringResource(Res.string.health),
            painter = painterResource(Res.drawable.health_icon)
        )
        StatItem(
            Modifier.weight(1f).aspectRatio(1f),
            onClick = onThirstClick,
            percent = chicken.thirstPercent,
            description = stringResource(Res.string.thirst),
            painter = painterResource(Res.drawable.thirst_icon)
        )
        StatItem(
            Modifier.weight(1f).aspectRatio(1f),
            onClick = onHungerClick,
            percent = chicken.hungerPercent,
            description = stringResource(Res.string.hunger),
            painter = painterResource(Res.drawable.hunger_icon)
        )
        StatItem(
            Modifier.weight(1f).aspectRatio(1f),
            onClick = onHappinessClick,
            percent = chicken.happinessPercent,
            description = stringResource(Res.string.happiness),
            painter = painterResource(Res.drawable.happiness_icon)
        )
    }
}

@Composable
private fun StatItem(
    modifier: Modifier,
    onClick: () -> Unit,
    description: String,
    painter: Painter,
    percent: Int,
) {
    val shape = RoundedCornerShape(25)
    Box(
        modifier.background(Color(0xff5c5c5c), shape).border(5.dp, Color(0xff2a2a2a), shape)
            .clip(shape)
            .clickable { onClick() }
    ) {
        Box(
            Modifier.align(Alignment.BottomCenter).fillMaxWidth().fillMaxHeight(percent / 100f)
                .background(Color(0xff369327))
        )
        Image(
            modifier = Modifier.align(Alignment.Center).fillMaxHeight(0.6f),
            painter = painter,
            contentDescription = description,
            contentScale = ContentScale.FillHeight
        )
    }
}