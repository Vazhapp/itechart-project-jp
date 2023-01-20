package com.example.itechart.home_screen.data.source.remote

import com.example.itechart.home_screen.data.source.dto.PodcastListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeServiceApi {

    @GET("best_podcasts?")
    suspend fun getPopularPodcasts(
        @Query("genre_id") genre_id:Int,
        @Query("page") page:Int,
        @Query("region") region:String,
        @Query("safe_mode")safe_mode:Int
    ): Response<PodcastListDTO>
}