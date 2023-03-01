package com.example.itechart.details_screen.domain.use_case

import com.example.itechart.details_screen.domain.model.PodcastDetails
import com.example.itechart.details_screen.domain.repository.DetailsRepository
import javax.inject.Inject

class GetPodcastDetailsUseCase @Inject constructor(
    private val detailsRepository: DetailsRepository
) {
    suspend operator fun invoke(podcastId: String): PodcastDetails {
        return detailsRepository.getPodcastEpisodes(podcastId = podcastId).payload ?: PodcastDetails()
    }
}