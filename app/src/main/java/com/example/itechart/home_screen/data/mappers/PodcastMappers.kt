package com.example.itechart.home_screen.data.mappers

import com.example.itechart.home_screen.data.source.dto.PodcastDTO
import com.example.itechart.home_screen.data.source.dto.PodcastListDTO
import com.example.itechart.home_screen.domain.model.Podcast
import com.example.itechart.home_screen.domain.model.PodcastList

fun PodcastListDTO.toDomainModel(): PodcastList =
    PodcastList(
        id = id,
        name = name,
        parent_id = parent_id,
        podcasts = podcasts,
        has_next = has_next,
        has_previous = has_previous,
        listennotes_url = listennotes_url,
    )

fun PodcastDTO.toDomainModel(): Podcast =
    Podcast(
        id = id,
        title = title,
        publisher = publisher,
        image = image,
        thumbnail = thumbnail,
        listennotes_url = listennotes_url,
        total_episodes = total_episodes,
        description = description,
        type = type,
    )

fun List<PodcastListDTO>.toDomainModel(): List<PodcastList> {
    val listOfPodcasts = listOf<PodcastList>().toMutableList()
   this.forEach {
       listOfPodcasts += it.toDomainModel()
    }
    return listOfPodcasts
}

