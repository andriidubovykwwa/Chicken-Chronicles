package com.emirhanok20.chickenchronicles.utility.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val bgColor = Color(0xff3C982B)
    val borderColor = Color(0xff07D85E)
    val disabledBgColor = Color(0xff982b2b)
    val disabledBorderColor = Color(0xffd80707)
    val shape = RoundedCornerShape(20)
    Box(
        modifier.background(if (enabled) bgColor else disabledBgColor, shape)
            .border(2.dp, if (enabled) borderColor else disabledBorderColor, shape).clip(shape)
            .clickable(enabled = enabled) { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}