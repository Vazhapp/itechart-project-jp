package com.example.itechart.home_screen.domain.paging

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    suspend fun reset()
}