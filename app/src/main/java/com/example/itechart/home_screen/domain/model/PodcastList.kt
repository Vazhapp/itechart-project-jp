package com.example.itechart.home_screen.domain.model

import com.example.itechart.home_screen.data.source.dto.PodcastDTO

data class PodcastList(
    val id : Int?,
    val name: String?,
    val parent_id: Int?,
    val podcasts : List<PodcastDTO>?,
    val has_next : Boolean?,
    val has_previous : Boolean?,
    val listennotes_url: String?
)
