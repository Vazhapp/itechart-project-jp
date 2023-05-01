package com.example.itechart.details_screen.domain.use_case

import com.example.itechart.common.DataState
import com.example.itechart.common.Resource
import com.example.itechart.details_screen.domain.model.PodcastDetails
import com.example.itechart.details_screen.domain.repository.DetailsRepository
import javax.inject.Inject

class GetPodcastDetailsUseCase @Inject constructor(
    private val detailsRepository: DetailsRepository
) {
    suspend operator fun invoke(podcastId: String): Resource<PodcastDetails> {
        return detailsRepository.getPodcastEpisodes(podcastId = podcastId)
    }
}