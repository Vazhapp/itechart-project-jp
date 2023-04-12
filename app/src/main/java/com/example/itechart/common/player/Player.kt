package com.example.itechart.common.player

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import javax.inject.Inject

class Player @Inject constructor(
    context: Context
) {
    private val player = ExoPlayer.Builder(context).build()

    fun play(
        audioUri: String
    ) {
        // this implemented here just for test
        val mediaItem =
            MediaItem.fromUri(audioUri)

        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    fun pause() {
        player.pause()
    }
}