package com.emirhanok20.chickenchronicles.utility.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.chicken
import chickenchronicles.composeapp.generated.resources.chicken1
import chickenchronicles.composeapp.generated.resources.chicken2
import chickenchronicles.composeapp.generated.resources.chicken3
import com.emirhanok20.chickenchronicles.data.Chicken
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChickenComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    chicken: Chicken
) {
    Image(
        modifier = modifier.scale(
            if (chicken.timeAliveInDays < 3) {
                0.5f
            } else if (chicken.timeAliveInDays < 6) {
                0.75f
            } else {
                1f
            }
        ).bounceClick(onClick = onClick),
        painter = painterResource(
            if (chicken.timeAliveInDays < 3) {
                Res.drawable.chicken1
            } else if (chicken.timeAliveInDays < 6) {
                Res.drawable.chicken2
            } else {
                Res.drawable.chicken3
            }
        ),
        contentDescription = stringResource(Res.string.chicken),
        contentScale = ContentScale.FillWidth
    )
}