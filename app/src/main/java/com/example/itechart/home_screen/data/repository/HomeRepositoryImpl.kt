package com.example.itechart.home_screen.data.repository

import com.example.itechart.home_screen.data.source.dto.toDomainModel
import com.example.itechart.home_screen.data.source.remote.HomeServiceApi
import com.example.itechart.home_screen.domain.model.PodcastPagingData
import com.example.itechart.home_screen.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeServiceApi: HomeServiceApi
) : HomeRepository {
    override suspend fun getPopularPodcasts(
        genreId: Int,
        page: Int,
        region: String,
        safeMode: Int
    ): Result<PodcastPagingData?> {
        return Result.success(
            homeServiceApi.getPopularPodcasts(genreId, page, region, safeMode).body()
                ?.toDomainModel()
        )
    }

    override suspend fun getPodcastAudio(
        podcastId: String
    ): Result<String> {
        return Result.success(
            homeServiceApi.getPodcastAudio(podcastId).body()?.episodes?.get(0)?.audio.orEmpty()
        )
    }
}