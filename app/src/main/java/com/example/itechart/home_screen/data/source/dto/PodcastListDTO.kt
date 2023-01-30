package com.example.itechart.home_screen.data.source.dto

data class PodcastListDTO(
    val id : Int?,
    val name: String?,
    val parent_id: Int?,
    val podcasts : List<PodcastDTO>?,
    val total : Int?,
    val has_next : Boolean?,
    val has_previous : Boolean?,
    val page_number: Int?,
    val previous_page_number:Int?,
    val next_page_number: Int?,
    val listennotes_url: String?
)