package com.example.itechart.home_screen.data.mappers

import com.example.itechart.home_screen.data.source.dto.PodcastDTO
import com.example.itechart.home_screen.data.source.dto.PodcastListDTO
import com.example.itechart.home_screen.domain.model.Podcast
import com.example.itechart.home_screen.domain.model.PagingData

fun PodcastListDTO.toDomainModel(): PagingData =
    PagingData(
        id = id,
        name = name,
        parentId = parentId,
        podcasts = podcasts,
        hasNext = hasNext,
        hasPrevious = hasPrevious,
        listenNotesUrl = listenNotesUrl,
    )

fun PodcastDTO.toDomainModel(): Podcast =
    Podcast(
        id = id,
        title = title,
        publisher = publisher,
        image = image,
        thumbnail = thumbnail,
        listenNotesUrl = listenNotesUrl,
        totalEpisodes = totalEpisodes,
        description = description,
        type = type,
    )

fun List<PodcastListDTO>.toDomainModel(): List<PagingData> {
    val listOfPodcasts = listOf<PagingData>().toMutableList()
   this.forEach {
       listOfPodcasts += it.toDomainModel()
    }
    return listOfPodcasts
}

