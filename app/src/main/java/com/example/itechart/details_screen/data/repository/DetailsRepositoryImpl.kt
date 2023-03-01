package com.example.itechart.details_screen.data.repository

import com.example.itechart.common.DataState
import com.example.itechart.common.ErrorMetaData
import com.example.itechart.common.SuccessMetaData
import com.example.itechart.details_screen.data.source.dto.toDomainModel
import com.example.itechart.details_screen.data.source.remote.DetailsServiceApi
import com.example.itechart.details_screen.domain.model.PodcastDetails
import com.example.itechart.details_screen.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val detailsServiceApi: DetailsServiceApi
) : DetailsRepository {
    override suspend fun getPodcastEpisodes(podcastId: String): DataState<PodcastDetails> {
        detailsServiceApi.getPodcastEpisodes(podcastId = podcastId).apply {
            return if (isSuccess) {
                DataState.Success(
                    SuccessMetaData(),
                    payload = this.getOrNull()?.toDomainModel()
                )
            } else {
                DataState.Error(ErrorMetaData())
            }
        }
    }
}