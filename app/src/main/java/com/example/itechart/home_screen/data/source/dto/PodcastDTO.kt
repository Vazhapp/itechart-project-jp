package com.example.itechart.home_screen.data.source.dto

data class PodcastDTO(
    val id: String?,
    val title: String?,
    val publisher: String?,
    val image : String?,
    val thumbnail: String?,
    val listenNotesUrl: String?,
    val totalEpisodes: String?,
    val description :String?,
    val website:String?,
    val type: String?,
    val genreIds: List<Int>?
)