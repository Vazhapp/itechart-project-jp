package com.example.itechart.home_screen.domain.use_case

import com.example.itechart.common.DataState
import com.example.itechart.home_screen.domain.model.PodcastList
import com.example.itechart.home_screen.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPodcastListUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend fun invoke(): Flow<DataState<MutableList<PodcastList>>> =
        flow {
            homeRepository.getPopularPodcasts(2, 144, "us", 0)
        }

}