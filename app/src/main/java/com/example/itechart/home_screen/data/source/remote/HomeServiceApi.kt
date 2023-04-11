package com.example.itechart.home_screen.data.source.remote

import com.example.itechart.details_screen.data.source.dto.PodcastDetailsDTO
import com.example.itechart.home_screen.data.source.dto.PodcastPagingDataDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeServiceApi {
    @GET("v1/best_podcasts?")
    suspend fun getPopularPodcasts(
        @Query("genre_id") genreId: Int,
        @Query("page") page: Int,
        @Query("region") region: String,
        @Query("safe_mode") safeMode: Int
    ): Response<PodcastPagingDataDTO>

    @GET("v1/podcasts/{podcastId}")
    suspend fun getPodcastAudio(
        @Path("podcastId") podcastId: String
    ): Response<PodcastDetailsDTO>
}