package com.example.itechart.home_screen.domain.repository

import com.example.itechart.home_screen.domain.model.PodcastPagingData

interface HomeRepository {
    suspend fun getPopularPodcasts(
        genreId: Int,
        page: Int,
        region: String,
        safeMode: Int
    ): Result<PodcastPagingData?>

    suspend fun getPodcastAudio(
        podcastId: String
    ): Result<String?>
}