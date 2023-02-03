package com.example.itechart.home_screen.domain.model

data class PodcastPagingData(
    val id : Int?,
    val name: String?,
    val parentId: Int?,
    val podcasts : List<Podcast>?,
    val hasNext : Boolean?,
    val hasPrevious : Boolean?,
    val listenNotesUrl: String?
)
