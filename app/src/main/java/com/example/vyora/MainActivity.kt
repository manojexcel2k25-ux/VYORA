
package com.example.vyora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF090B12)
private val Glass = Color(0xFF191D2B)
private val Mint = Color(0xFF9CFFD5)
private val Purple = Color(0xFF9C8BFF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VyoraApp()
        }
    }
}

@Composable
fun VyoraApp() {
    var playing by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("Midnight Drive") }
    var tab by remember { mutableStateOf("Home") }

    val songs = listOf(
        "Midnight Drive" to "Neon Dreams",
        "Ocean Eyes" to "Blue Horizon",
        "Afterglow" to "Nova",
        "Moonlight" to "Luna Waves"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF20203D),
                        Ink,
                        Color(0xFF101B20)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 26.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "VYORA",
                        color = Mint,
                        fontSize = 29.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 4.sp
                    )
                    Text(
                        "FEEL EVERY FREQUENCY",
                        color = Color.LightGray,
                        fontSize = 9.sp,
                        letterSpacing = 2.sp
                    )
                }
                Text("◉", color = Mint, fontSize = 28.sp)
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                contentPadding = PaddingValues(bottom = 14.dp)
            ) {
                item {
                    Text(
                        "Your world. Your sound.",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "A little mood, a lot of music.",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFF42436A),
                                        Color(0xFF24283B),
                                        Color(0xFF25463F)
                                    )
                                ),
                                RoundedCornerShape(28.dp)
                            )
                            .padding(22.dp)
                    ) {
                        Text(
                            "YOUR DAILY ESCAPE",
                            color = Mint,
                            fontSize = 10.sp,
                            letterSpacing = 2.sp
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "Lose yourself\nin the moment.",
                            color = Color.White,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(18.dp))
                        Button(
                            onClick = {
                                selected = songs.first().first
                                playing = !playing
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Mint,
                                contentColor = Ink
                            )
                        ) {
                            Text(
                                if (playing) "Pause preview Ⅱ"
                                else "Explore music ▶"
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Made for your mood",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Text("SEE ALL", color = Mint, fontSize = 10.sp)
                    }
                }

                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        MoodCard(
                            "🌌", "Night Vibes",
                            Modifier.weight(1f),
                            Purple
                        ) { selected = "Midnight Drive" }

                        MoodCard(
                            "🌊", "Chill Flow",
                            Modifier.weight(1f),
                            Color(0xFF477FAD)
                        ) { selected = "Ocean Eyes" }
                    }
                }

                item {
                    Text(
                        "Recently played",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(songs.size) { index ->
                    val song = songs[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Glass.copy(alpha = 0.85f),
                                RoundedCornerShape(18.dp)
                            )
                            .clickable {
                                selected = song.first
                                playing = true
                            }
                            .padding(13.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(
                                    Brush.linearGradient(
                                        listOf(Purple, Mint.copy(alpha = 0.6f))
                                    ),
                                    RoundedCornerShape(14.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("♫", color = Ink, fontSize = 27.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 13.dp)
                        ) {
                            Text(
                                song.first,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                song.second,
                                color = Color.LightGray,
                                fontSize = 12.sp
                            )
                        }
                        Text(
                            if (selected == song.first && playing) "Ⅱ" else "▶",
                            color = Mint,
                            fontSize = 20.sp
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Glass.copy(alpha = 0.96f),
                        RoundedCornerShape(22.dp)
                    )
                    .padding(vertical = 13.dp, horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("Home", "Search", "Library").forEach { name ->
                    Column(
                        modifier = Modifier
                            .clickable { tab = name }
                            .padding(horizontal = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            when (name) {
                                "Home" -> "⌂"
                                "Search" -> "⌕"
                                else -> "▤"
                            },
                            color = if (tab == name) Mint
                            else Color.LightGray,
                            fontSize = 23.sp
                        )
                        Text(
                            name,
                            color = if (tab == name) Mint
                            else Color.LightGray,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
fun MoodCard(
    emoji: String,
    title: String,
    modifier: Modifier = Modifier,
    tint: Color,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(
                Brush.linearGradient(
                    listOf(tint.copy(alpha = 0.45f), Glass)
                ),
                RoundedCornerShape(22.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(emoji, fontSize = 30.sp)
        Spacer(Modifier.height(15.dp))
        Text(
            title,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text("PLAY YOUR WAY", color = Mint, fontSize = 9.sp)
    }
}
