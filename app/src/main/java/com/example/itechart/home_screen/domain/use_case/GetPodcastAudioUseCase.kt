package com.example.itechart.home_screen.domain.use_case

import com.example.itechart.common.Resource
import com.example.itechart.home_screen.domain.repository.HomeRepository
import javax.inject.Inject

class GetPodcastAudioUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(
        podcastId: String
    ): Resource<String> {
        return homeRepository.getPodcastAudio(
            podcastId
        )
    }
}