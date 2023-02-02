package com.example.itechart.home_screen.domain.model

import com.example.itechart.home_screen.data.source.dto.PodcastDTO

data class PagingData(
    val id : Int?,
    val name: String?,
    val parentId: Int?,
    val podcasts : List<PodcastDTO>?,
    val hasNext : Boolean?,
    val hasPrevious : Boolean?,
    val listenNotesUrl: String?
)
