package com.example.itechart.details_screen.data.source.dto

import com.example.itechart.details_screen.domain.model.PodcastDetails

data class PodcastDetailsDTO(
    val country: String?,
    val description: String?,
    val earliestPubDateMs: Long?,
    val email: String?,
    val episodes: List<Episode?>?,
    val explicitContent: Boolean?,
    val extra: Extra?,
    val genres: List<String?>?,
    val id: String?,
    val image: String?,
    val isClaimed: Boolean?,
    val itunesId: Int?,
    val language: String?,
    val lastestPubDateMs: Long?,
    val listenNotesUrl: String?,
    val lookingFor: LookingFor?,
    val nextEpisodePubDate: Long?,
    val publisher: String?,
    val rss: String?,
    val thumbnail: String?,
    val title: String?,
    val totalEpisodes: Int?,
    val website: String?
)

fun PodcastDetailsDTO.toDomainModel() =
    PodcastDetails(
        country = country,
        description = description,
        episodes = episodes,
        id = id,
        image = image,
        nextEpisodePubDate = nextEpisodePubDate,
        publisher = publisher,
        title = title,
        totalEpisodes = totalEpisodes,
    )