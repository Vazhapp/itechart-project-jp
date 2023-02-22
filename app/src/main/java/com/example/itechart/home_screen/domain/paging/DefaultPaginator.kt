package com.example.itechart.home_screen.domain.paging

import com.example.itechart.common.DataState

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: suspend (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<DataState<Item>>,
    private inline val getNextKey: suspend (Item) -> Key,
    private inline val onError: suspend (Throwable) -> Unit,
    private inline val onSuccess: suspend (item: DataState<Item>, newKey: Key) -> Unit
): Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false

        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey(items.payload!!)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    override suspend fun reset() {
        currentKey = initialKey
    }

}