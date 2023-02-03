package com.example.itechart.home_screen.data.mappers

import com.example.itechart.home_screen.data.source.dto.PodcastDTO
import com.example.itechart.home_screen.data.source.dto.PodcastPagingDataDTO
import com.example.itechart.home_screen.domain.model.Podcast
import com.example.itechart.home_screen.domain.model.PodcastPagingData

fun PodcastPagingDataDTO.toDomainModel(): PodcastPagingData =
    PodcastPagingData(
        id = id,
        name = name,
        parentId = parentId,
        podcasts = podcasts.toDomainModel(),
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

fun List<PodcastDTO>.toDomainModel(): List<Podcast> {
    val podcastList = mutableListOf<Podcast>()
    this.forEach {
        podcastList += it.toDomainModel()
    }
    return podcastList
}

