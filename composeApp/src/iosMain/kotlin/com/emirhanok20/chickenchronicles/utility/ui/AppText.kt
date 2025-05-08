package com.emirhanok20.chickenchronicles.utility.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.eras_itc_bold
import org.jetbrains.compose.resources.Font

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 22.sp,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Start
) {
    val fontFamily = FontFamily(Font(Res.font.eras_itc_bold))
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        color = color,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}