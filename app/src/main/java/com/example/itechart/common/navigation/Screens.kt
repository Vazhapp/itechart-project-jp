package com.example.itechart.common.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val PODCAST_ID_ARGUMENT_KEY = "podcastId"
const val PODCAST_AUDIO_URI_ARGUMENT_KEY = "podcastAudioUri"
sealed class Screens(val route: String) {
    object HomeScreen : Screens(route = "home_screen")
    object DetailsScreen : Screens(route = "details_screen/{$PODCAST_ID_ARGUMENT_KEY}/{$PODCAST_AUDIO_URI_ARGUMENT_KEY}") {
        fun passPodcastIdAndAudioUri(podcastId: String, podcastAudioUri: String): String {
            val encodedUrl = URLEncoder.encode(podcastAudioUri, StandardCharsets.UTF_8.toString())

            return "details_screen/$podcastId/$encodedUrl"
        }
    }
    object FavoritesScreen : Screens(route = "favorites_screen")
}
