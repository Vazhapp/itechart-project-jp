package com.example.itechart.details_screen.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.itechart.details_screen.presentation.uiComponents.MediaController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

@Composable
fun DetailsScreen(
    podcastId: String,
    detailsViewModel: DetailsViewModel
) {
    detailsViewModel.getPodcastAudioUri(podcastId)

    val uiState by detailsViewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    } else {
        DetailsScreen(
            podcastId = podcastId,
            podcastAudioUri = uiState.podcastAudioUri
        )
    }
}

@Composable
fun DetailsScreen(
    podcastId: String,
    podcastAudioUri: String
) {
    val contenxt = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(contenxt).build().apply {
            setMediaItem(
                MediaItem.fromUri(
                    podcastAudioUri
                )
            )
            prepare()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        MediaController(podcastId = podcastId, podcastAudioUri = podcastAudioUri, exopl = exoPlayer)
    }
}