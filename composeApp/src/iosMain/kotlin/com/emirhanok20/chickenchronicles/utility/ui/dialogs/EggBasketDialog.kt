package com.emirhanok20.chickenchronicles.utility.ui.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import chickenchronicles.composeapp.generated.resources.Res
import chickenchronicles.composeapp.generated.resources.arrow_up
import chickenchronicles.composeapp.generated.resources.basket
import chickenchronicles.composeapp.generated.resources.coin
import chickenchronicles.composeapp.generated.resources.dialog_bg
import chickenchronicles.composeapp.generated.resources.egg
import chickenchronicles.composeapp.generated.resources.golden_egg
import chickenchronicles.composeapp.generated.resources.gray_box
import chickenchronicles.composeapp.generated.resources.green_box
import chickenchronicles.composeapp.generated.resources.sell
import chickenchronicles.composeapp.generated.resources.upgrade
import com.emirhanok20.chickenchronicles.data.Egg
import com.emirhanok20.chickenchronicles.navigation.game.GameEvent
import com.emirhanok20.chickenchronicles.navigation.game.GameState
import com.emirhanok20.chickenchronicles.utility.ui.AppButton
import com.emirhanok20.chickenchronicles.utility.ui.AppText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EggBasketDialog(
    modifier: Modifier = Modifier,
    onEvent: (GameEvent) -> Unit,
    onDismiss: () -> Unit,
    state: GameState,
) {
    val rowSize = 4
    val eggs = state.eggBasket.eggs
    val capacity = state.eggBasket.capacity
    var rows = capacity / rowSize
    if (capacity % rowSize > 0) rows += 1
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier.size(300.dp, 348.dp).paint(
                painter = painterResource(Res.drawable.dialog_bg),
                contentScale = ContentScale.FillBounds
            ).padding(vertical = 3.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Image(
                        modifier = Modifier.width(50.dp),
                        painter = painterResource(Res.drawable.basket),
                        contentDescription = stringResource(Res.string.basket),
                        contentScale = ContentScale.FillWidth
                    )
                    AppText(text = "LVL ${state.eggBasket.lvl}")
                }
                items(rows) { row ->
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(rowSize) { column ->
                            val index = row * rowSize + column
                            if (index < eggs.size) {
                                Box(
                                    Modifier.weight(1f).aspectRatio(1f).paint(
                                        painter = painterResource(Res.drawable.green_box),
                                        contentScale = ContentScale.FillWidth
                                    ), contentAlignment = Alignment.Center
                                ) {
                                    when (eggs[index]) {
                                        Egg.DEFAULT -> Image(
                                            modifier = Modifier.fillMaxWidth(0.5f),
                                            painter = painterResource(Res.drawable.egg),
                                            contentDescription = stringResource(Res.string.egg),
                                            contentScale = ContentScale.FillWidth
                                        )

                                        Egg.GOLDEN -> Image(
                                            modifier = Modifier.fillMaxWidth(0.5f),
                                            painter = painterResource(Res.drawable.golden_egg),
                                            contentDescription = stringResource(Res.string.golden_egg),
                                            contentScale = ContentScale.FillWidth
                                        )
                                    }
                                }
                            } else if (index < capacity) {
                                Box(
                                    Modifier.weight(1f).aspectRatio(1f).paint(
                                        painter = painterResource(Res.drawable.gray_box),
                                        contentScale = ContentScale.FillWidth
                                    )
                                )
                            } else {
                                Box(Modifier.weight(1f).aspectRatio(1f))
                            }
                        }
                    }
                }
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        AppButton(
                            enabled = state.canSellEggs,
                            onClick = { onEvent(GameEvent.SellEggs) }
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AppText(text = stringResource(Res.string.sell), fontSize = 25.sp)
                                Image(
                                    modifier = Modifier.height(36.dp),
                                    painter = painterResource(Res.drawable.coin),
                                    contentDescription = stringResource(Res.string.coin),
                                    contentScale = ContentScale.FillHeight
                                )
                                AppText(
                                    text = state.eggBasket.sellPrice.toString(),
                                    fontSize = 18.sp
                                )
                            }
                        }
                        AppButton(
                            enabled = state.canUpgradeEggBasket,
                            onClick = { onEvent(GameEvent.UpgradeEggBasket) }
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    modifier = Modifier.height(36.dp),
                                    painter = painterResource(Res.drawable.arrow_up),
                                    contentDescription = stringResource(Res.string.upgrade),
                                    contentScale = ContentScale.FillHeight
                                )
                                Image(
                                    modifier = Modifier.height(36.dp),
                                    painter = painterResource(Res.drawable.coin),
                                    contentDescription = stringResource(Res.string.coin),
                                    contentScale = ContentScale.FillHeight
                                )
                                AppText(
                                    text = state.eggBasket.upgradePrice.toString(),
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}