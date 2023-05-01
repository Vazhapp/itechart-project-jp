package com.example.itechart.details_screen.data.source.remote

import com.example.itechart.details_screen.data.source.dto.PodcastDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsServiceApi {

    @GET("v1/podcasts/{podcast_id}")
    suspend fun getPodcastEpisodes(@Path("podcast_id") podcastId: String): PodcastDetailsDTO
}