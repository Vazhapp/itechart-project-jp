package com.example.itechart.home_screen.data.source.dto

import com.example.itechart.home_screen.domain.model.PodcastPagingData

data class PodcastPagingDataDTO(
    val id : Int?,
    val name: String?,
    val parentId: Int?,
    val channels : List<PodcastDTO>,
    val total : Int?,
    val hasNext : Boolean?,
    val hasPrevious : Boolean?,
    val pageNumber: Int?,
    val previousPageNumber:Int?,
    val nextPageNumber: Int?,
    val listenNotesUrl: String?
)

fun PodcastPagingDataDTO.toDomainModel(): PodcastPagingData =
    PodcastPagingData(
        id = id,
        name = name,
        parentId = parentId,
        podcasts = channels.toDomainModel(),
        hasNext = hasNext,
        hasPrevious = hasPrevious,
        listenNotesUrl = listenNotesUrl,
        pageNumber = pageNumber,
        nextPageNumber = nextPageNumber,
    )