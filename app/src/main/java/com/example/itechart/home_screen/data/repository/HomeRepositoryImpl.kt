package com.example.itechart.home_screen.data.repository

import android.util.Log.d
import com.example.itechart.common.DataState
import com.example.itechart.common.SuccessMetaData
import com.example.itechart.home_screen.data.mappers.toDomainModel
import com.example.itechart.home_screen.data.source.remote.HomeServiceApi
import com.example.itechart.home_screen.domain.model.PodcastList
import com.example.itechart.home_screen.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeServiceApi: HomeServiceApi
) : HomeRepository {

    override suspend fun getPopularPodcasts(
        genre_id: Int,
        page: Int,
        region: String,
        safe_mode: Int
    ): Flow<DataState<MutableList<PodcastList>>> =
        flow {
            homeServiceApi.getPopularPodcasts(
                genre_id = genre_id,
                page = page,
                region = region,
                safe_mode = safe_mode,
            ).apply {
                if (isSuccessful) {

                    val podcastList = body()?.toDomainModel()?.let { mutableListOf(it) }!!
                    emit(
                        DataState.Success(
                            SuccessMetaData(),
                            payload = podcastList
                        )
                    )
                }
            }
        }
}