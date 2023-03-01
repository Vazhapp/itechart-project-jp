package com.example.itechart.details_screen.domain.model

import com.example.itechart.details_screen.data.source.dto.Episode

data class PodcastDetails(
    val country: String?,
    val description: String?,
    val episodes: List<Episode?>?,
    val id: String?,
    val image: String?,
    val nextEpisodePubDate: Long?,
    val publisher: String?,
    val title: String?,
    val totalEpisodes: Int?,
) {
    constructor(): this(
        "",
        "",
        emptyList(),
        "",
        "",
        0,
        "",
        "",
        0,
    )
}
