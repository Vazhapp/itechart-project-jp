package com.example.itechart.home_screen.data.source.dto

import com.example.itechart.home_screen.domain.model.Podcast

data class PodcastDTO(
    val id: String?,
    val title: String?,
    val publisher: String?,
    val image: String?,
    val thumbnail: String?,
    val listenNotesUrl: String?,
    val totalEpisodes: String?,
    val description: String?,
    val website: String?,
    val type: String?,
    val genreIds: List<Int>?
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