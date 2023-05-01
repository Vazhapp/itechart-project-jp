package com.example.itechart.details_screen.data.repository

import android.util.Log.d
import com.example.itechart.common.DataState
import com.example.itechart.common.ErrorMetaData
import com.example.itechart.common.Resource
import com.example.itechart.common.SuccessMetaData
import com.example.itechart.details_screen.data.source.dto.toDomainModel
import com.example.itechart.details_screen.data.source.remote.DetailsServiceApi
import com.example.itechart.details_screen.domain.model.PodcastDetails
import com.example.itechart.details_screen.domain.repository.DetailsRepository
import java.lang.Exception
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val detailsServiceApi: DetailsServiceApi
) : DetailsRepository {
    override suspend fun getPodcastEpisodes(podcastId: String): Resource<PodcastDetails>
    {
        return try {
            Resource.Success(
                detailsServiceApi.getPodcastEpisodes(podcastId).toDomainModel()
            )
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "Unknown error")
        }
//        detailsServiceApi.getPodcastEpisodes(podcastId = podcastId).apply {
//            return if (this.isSuccessful) {
//                DataState.Success(
//                    SuccessMetaData(),
//                    payload = this.body()?.toDomainModel()
//                )
//            } else {
//                DataState.Error(ErrorMetaData())
//            }
//        }
    }
}