package com.example.itechart.details_screen.presentation.uiComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.itechart.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

@Composable
fun MediaController(
    modifier: Modifier = Modifier,
    podcastId: String,
    podcastAudioUri: String,
    exopl: ExoPlayer
) {
    val context = LocalContext.current
    var rememberPlayerState by remember {
        mutableStateOf(true)
    }

//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            setMediaItem(
//                MediaItem.fromUri(
//                    podcastAudioUri
//                )
//            )
//            prepare()
//        }
//    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.rewind_10_second),
            contentDescription = "",
            modifier.size(50.dp)
        )

        AnimatedVisibility(visible = rememberPlayerState) {
            Image(
                painter = painterResource(id = R.drawable.play),
                contentDescription = "",
                modifier
                    .size(50.dp)
                    .clickable {
                        exopl.play()
                        rememberPlayerState = !rememberPlayerState
                    }
            )
        }

        AnimatedVisibility(visible = !rememberPlayerState) {
            Image(
                painter = painterResource(id = R.drawable.pause),
                contentDescription = "",
                modifier
                    .size(50.dp)
                    .clickable {
                        exopl.pause()
                        rememberPlayerState = !rememberPlayerState
                    }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.forward_10_second),
            contentDescription = "",
            modifier.size(50.dp)
        )
    }
}