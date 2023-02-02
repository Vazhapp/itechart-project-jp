package com.example.itechart.home_screen.data.repository

import com.example.itechart.common.DataState
import com.example.itechart.common.SuccessMetaData
import com.example.itechart.home_screen.data.mappers.toDomainModel
import com.example.itechart.home_screen.data.source.remote.HomeServiceApi
import com.example.itechart.home_screen.domain.model.PagingData
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
    ): DataState<PagingData> {
        return DataState.Success(
            SuccessMetaData(),
            homeServiceApi.getPopularPodcasts(genreId, page, region, safeMode).body()?.toDomainModel()
        )
    }
}