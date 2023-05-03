package com.example.itechart.details_screen.presentation.uiComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.itechart.R
import com.example.itechart.ui.theme.Purple200
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun MediaController(
    modifier: Modifier = Modifier,
    podcastId: String,
    podcastAudioUri: String
) {
    val context = LocalContext.current

    var isPlaying by remember { mutableStateOf(true) }

    var totalDuration by remember { mutableStateOf(0L) }

    var currentTime by remember { mutableStateOf(0L) }

    var bufferedPercentage by remember { mutableStateOf(0) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(
                MediaItem.fromUri(
                    podcastAudioUri
                )
            )
            prepare()
        }
    }

    Column(modifier.fillMaxSize()) {
        Box {
            DisposableEffect(key1 = Unit) {
                val listener =
                    object : Player.Listener {
                        override fun onEvents(player: Player, events: Player.Events) {
                            super.onEvents(player, events)
                            totalDuration = player.duration.coerceAtLeast(0L)
                            currentTime = player.currentPosition.coerceAtLeast(0L)
                            bufferedPercentage = player.bufferedPercentage
                        }
                    }

                exoPlayer.addListener(listener)

                onDispose {
                    exoPlayer.removeListener(listener)
                    exoPlayer.release()
                }
            }
        }

        BottomControls(
            totalDuration = totalDuration,
            currentTime = currentTime,
            bufferPercentage = bufferedPercentage,
            onSeekChanged = { timeMs: Float -> exoPlayer.seekTo(timeMs.toLong()) }
        )

        PlayerControls(
            isPlaying = { isPlaying },
            onReplayClick = { exoPlayer.seekBack() },
            onForwardClick = { exoPlayer.seekForward() },
            onPauseToggle = {
                if (exoPlayer.isPlaying) {
                    exoPlayer.pause()
                } else {
                    exoPlayer.play()
                }
                isPlaying = isPlaying.not()
            }
        )
    }
}

@Composable
fun PlayerControls(
    modifier: Modifier = Modifier,
    isPlaying: () -> Boolean,
    onReplayClick: () -> Unit,
    onPauseToggle: () -> Unit,
    onForwardClick: () -> Unit,
    ) {
    val isPodcastPlaying = remember(isPlaying()) { isPlaying() }
    Box {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Black),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = modifier.size(50.dp),
                onClick = { onReplayClick() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rewind_10_second),
                    contentDescription = "Replay podcast 10sec",
                    contentScale = ContentScale.Crop,
                    modifier = modifier.size(50.dp)
                )
            }

            IconButton(
                modifier = modifier.size(50.dp),
                onClick = { onPauseToggle() }
            ) {
                Image(
                    painter = if (isPodcastPlaying) {
                        painterResource(id = R.drawable.pause)
                    } else {
                        painterResource(id = R.drawable.play)
                    },
                    contentScale = ContentScale.Crop,
                    contentDescription = "Play/Pause podcast",
                    modifier = modifier
                        .size(50.dp)
                )
            }

            IconButton(
                modifier = modifier.size(50.dp),
                onClick = { onForwardClick() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.forward_10_second),
                    contentDescription = "Forward podcast 10 sec",
                    contentScale = ContentScale.Crop,
                    modifier = modifier.size(50.dp)
                )
            }
        }
    }
}

@Composable
fun BottomControls(
    modifier: Modifier = Modifier,
    totalDuration: Long,
    currentTime: Long,
    bufferPercentage: Int,
    onSeekChanged: (timeMs: Float) -> Unit,
) {

    Column(modifier = modifier.padding(bottom = 22.dp)) {
        Box(modifier = modifier.fillMaxWidth()) {
            Slider(
                value = bufferPercentage.toFloat(),
                enabled = false,
                onValueChange = { /*do nothing*/ },
                valueRange = 0f..100f,
                colors =
                SliderDefaults.colors(
                    disabledThumbColor = Color.Transparent,
                    disabledActiveTrackColor = Color.Gray
                )
            )
            Slider(
                modifier = Modifier.fillMaxWidth(),
                value = currentTime.toFloat(),
                onValueChange = onSeekChanged,
                valueRange = 0f..totalDuration.toFloat(),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTickColor = Color.Red
                ),
            )
        }
    }
}