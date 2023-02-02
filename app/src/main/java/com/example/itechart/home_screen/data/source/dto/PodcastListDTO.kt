package com.example.itechart.home_screen.data.source.dto

data class PodcastListDTO(
    val id : Int?,
    val name: String?,
    val parentId: Int?,
    val podcasts : List<PodcastDTO>?,
    val total : Int?,
    val hasNext : Boolean?,
    val hasPrevious : Boolean?,
    val pageNumber: Int?,
    val previousPageNumber:Int?,
    val nextPageNumber: Int?,
    val listenNotesUrl: String?
)