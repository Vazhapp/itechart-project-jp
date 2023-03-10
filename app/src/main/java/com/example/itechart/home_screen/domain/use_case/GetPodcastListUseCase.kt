package com.example.itechart.home_screen.domain.use_case

import com.example.itechart.common.DataState
import com.example.itechart.home_screen.domain.model.PodcastPagingData
import com.example.itechart.home_screen.domain.repository.HomeRepository
import javax.inject.Inject

private const val GENRE_ID = 144
private const val REGION = "us"
private const val SAFE_MODE = 0

class GetPodcastListUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(
        nextPageNumber: Int
    ): Result<DataState<PodcastPagingData>> {
        return homeRepository.getPopularPodcasts(GENRE_ID, nextPageNumber, REGION, SAFE_MODE)
    }
}