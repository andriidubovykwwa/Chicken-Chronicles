package com.emirhanok20.chickenchronicles.navigation.loading

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.app_name
import chickenchronicles.composeapp.generated.resources.loading_bg
import chickenchronicles.composeapp.generated.resources.logo
import com.emirhanok20.chickenchronicles.utility.ui.AppText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoadingScreen() {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    val text = "LOADING..."
    val textSize by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = text.length.toFloat() + 0.99f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Restart
        )
    )
    Box(
        Modifier.fillMaxSize().paint(
            painter = painterResource(Res.drawable.loading_bg),
            contentScale = ContentScale.FillBounds
        ).safeContentPadding()
    ) {
        Image(
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 50.dp).scale(scale)
                .fillMaxWidth(0.9f),
            contentDescription = stringResource(Res.string.app_name),
            painter = painterResource(Res.drawable.logo),
            contentScale = ContentScale.FillWidth
        )
        AppText(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 10.dp),
            text = text.take(textSize.toInt().coerceAtMost(text.length)),
            fontSize = 30.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}