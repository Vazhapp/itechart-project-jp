package com.example.itechart.home_screen.domain.repository

import com.example.itechart.common.DataState
import com.example.itechart.home_screen.data.source.dto.PodcastListDTO
import com.example.itechart.home_screen.domain.model.PodcastList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getPopularPodcasts(
        genre_id:Int,
        page:Int,
        region:String,
        safe_mode:Int
    ): Flow<DataState<MutableList<PodcastList>>>
}