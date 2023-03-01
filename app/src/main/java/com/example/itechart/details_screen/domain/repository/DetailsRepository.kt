package com.example.itechart.details_screen.domain.repository

import com.example.itechart.common.DataState
import com.example.itechart.details_screen.domain.model.PodcastDetails

interface DetailsRepository {
    suspend fun getPodcastEpisodes(podcastId: String): DataState<PodcastDetails>
}