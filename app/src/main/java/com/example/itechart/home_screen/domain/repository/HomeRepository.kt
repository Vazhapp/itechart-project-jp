package com.example.itechart.home_screen.domain.repository

import com.example.itechart.common.DataState
import com.example.itechart.home_screen.domain.model.PodcastPagingData

interface HomeRepository {

    suspend fun getPopularPodcasts(
        genreId: Int,
        page: Int,
        region: String,
        safeMode: Int
    ): Result<DataState<PodcastPagingData>>
}