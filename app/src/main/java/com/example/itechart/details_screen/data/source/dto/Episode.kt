package com.example.itechart.details_screen.data.source.dto

data class Episode(
    val audio: String?,
    val audioLength: Int?,
    val description: String?,
    val explicitContent: Boolean?,
    val id: String?,
    val image: String?,
    val listenNotesEditUrl: String?,
    val listenNotesUrl: String?,
    val maybeAudioInvalid: Boolean?,
    val pubDateMs: Long?,
    val thumbnail: String?,
    val title: String?
)