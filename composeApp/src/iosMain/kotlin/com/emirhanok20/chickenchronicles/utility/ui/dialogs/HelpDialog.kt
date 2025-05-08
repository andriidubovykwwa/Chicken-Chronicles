package com.emirhanok20.chickenchronicles.utility.ui.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import chickenchronicles.composeapp.generated.resources.chicken
import chickenchronicles.composeapp.generated.resources.chicken1
import chickenchronicles.composeapp.generated.resources.chicken2
import chickenchronicles.composeapp.generated.resources.chicken3
import chickenchronicles.composeapp.generated.resources.dialog_bg
import chickenchronicles.composeapp.generated.resources.egg
import chickenchronicles.composeapp.generated.resources.energy
import chickenchronicles.composeapp.generated.resources.happiness
import chickenchronicles.composeapp.generated.resources.happiness_icon
import chickenchronicles.composeapp.generated.resources.health
import chickenchronicles.composeapp.generated.resources.health_icon
import chickenchronicles.composeapp.generated.resources.help
import chickenchronicles.composeapp.generated.resources.help_icon
import chickenchronicles.composeapp.generated.resources.hunger
import chickenchronicles.composeapp.generated.resources.hunger_icon
import chickenchronicles.composeapp.generated.resources.mini_game
import chickenchronicles.composeapp.generated.resources.quiz
import chickenchronicles.composeapp.generated.resources.quiz_icon
import chickenchronicles.composeapp.generated.resources.thirst
import chickenchronicles.composeapp.generated.resources.thirst_icon
import chickenchronicles.composeapp.generated.resources.worm
import com.emirhanok20.chickenchronicles.utility.ui.AppText
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HelpDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
) {
    val defaultFontSize = 14.sp
    val headerFontSize = 17.sp
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
                        modifier = Modifier.height(50.dp),
                        painter = painterResource(Res.drawable.help_icon),
                        contentDescription = stringResource(Res.string.help),
                        contentScale = ContentScale.FillHeight
                    )
                }
                item {
                    AppText(
                        text = "Welcome to Chicken Chronicles! In this game, you will raise a chicken from a tiny chick to a full-grown adult. Take good care of it to succeed.",
                        fontSize = defaultFontSize
                    )
                }
                item {
                    AppText(text = "Chicken Growth Stages", fontSize = headerFontSize)
                }
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Image(
                                modifier = Modifier.height(28.dp),
                                painter = painterResource(Res.drawable.chicken1),
                                contentDescription = stringResource(Res.string.chicken),
                                contentScale = ContentScale.FillHeight
                            )
                            Image(
                                modifier = Modifier.height(34.dp),
                                painter = painterResource(Res.drawable.chicken2),
                                contentDescription = stringResource(Res.string.chicken),
                                contentScale = ContentScale.FillHeight
                            )
                            Image(
                                modifier = Modifier.height(40.dp),
                                painter = painterResource(Res.drawable.chicken3),
                                contentDescription = stringResource(Res.string.chicken),
                                contentScale = ContentScale.FillHeight
                            )
                        }
                        AppText(
                            text = "0–2 months: Chick\n2–5 months: Young chicken\n5+ months: Adult chicken\nOne game month equals one real-life day.",
                            fontSize = defaultFontSize
                        )
                    }
                }
                item {
                    AppText(text = "Main Chicken Stats", fontSize = headerFontSize)
                }
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Image(
                            modifier = Modifier.height(40.dp),
                            painter = painterResource(Res.drawable.thirst_icon),
                            contentDescription = stringResource(Res.string.thirst),
                            contentScale = ContentScale.FillHeight
                        )
                        AppText(
                            text = "Thirst: Buy water with coins to fill the drinker. Upgrade capacity to refill less often.",
                            fontSize = defaultFontSize
                        )
                    }
                }
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Image(
                            modifier = Modifier.height(40.dp),
                            painter = painterResource(Res.drawable.hunger_icon),
                            contentDescription = stringResource(Res.string.hunger),
                            contentScale = ContentScale.FillHeight
                        )
                        AppText(
                            text = "Hunger: Buy food with coins for the feeder. Upgrade capacity for better efficiency.",
                            fontSize = defaultFontSize
                        )
                    }
                }
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Image(
                            modifier = Modifier.height(40.dp),
                            painter = painterResource(Res.drawable.happiness_icon),
                            contentDescription = stringResource(Res.string.happiness),
                            contentScale = ContentScale.FillHeight
                        )
                        AppText(
                            text = "Happiness: Pet your chicken by tapping it to increase happiness. Adults need fewer pets.",
                            fontSize = defaultFontSize
                        )
                    }
                }
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Image(
                            modifier = Modifier.height(40.dp),
                            painter = painterResource(Res.drawable.health_icon),
                            contentDescription = stringResource(Res.string.health),
                            contentScale = ContentScale.FillHeight
                        )
                        AppText(
                            text = "Health: If any stat drops to zero, health decreases. At zero health, the chicken dies, and the game restarts. If all stats stay above zero, health recovers. Adults have more health.",
                            fontSize = defaultFontSize
                        )
                    }
                }
                item {
                    AppText(text = "Extra Features", fontSize = headerFontSize)
                }
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Image(
                            modifier = Modifier.height(40.dp),
                            painter = painterResource(Res.drawable.egg),
                            contentDescription = stringResource(Res.string.egg),
                            contentScale = ContentScale.FillHeight
                        )
                        AppText(
                            text = "Eggs: Chickens lay eggs into a limited basket. Upgrade with coins. Golden eggs may appear depending on stats and age.",
                            fontSize = defaultFontSize
                        )
                    }
                }
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Image(
                            modifier = Modifier.height(40.dp),
                            painter = painterResource(Res.drawable.energy),
                            contentDescription = stringResource(Res.string.energy),
                            contentScale = ContentScale.FillHeight
                        )
                        AppText(
                            text = "Energy: Restores over time. Max energy increases as the chicken grows. Used in the mini game.",
                            fontSize = defaultFontSize
                        )
                    }
                }
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Image(
                            modifier = Modifier.height(40.dp),
                            painter = painterResource(Res.drawable.worm),
                            contentDescription = stringResource(Res.string.mini_game),
                            contentScale = ContentScale.FillHeight
                        )
                        AppText(
                            text = "Mini game (Catching Worms): Tap worms quickly to catch and earn coins. Energy cost: 1 second = 1 energy.",
                            fontSize = defaultFontSize
                        )
                    }
                }
                item {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        Image(
                            modifier = Modifier.height(40.dp),
                            painter = painterResource(Res.drawable.quiz_icon),
                            contentDescription = stringResource(Res.string.quiz),
                            contentScale = ContentScale.FillHeight
                        )
                        AppText(
                            text = "Daily Quiz: Once per day, solve simple math problems. Correct answers earn coins. Mistakes let you retry the next day.",
                            fontSize = defaultFontSize
                        )
                    }
                }
            }
        }
    }
}