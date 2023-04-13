package com.example.itechart.home_screen.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itechart.common.player.Player
import com.example.itechart.home_screen.domain.model.Podcast
import com.example.itechart.home_screen.domain.model.PodcastPagingData
import com.example.itechart.home_screen.domain.paging.DefaultPaginator
import com.example.itechart.home_screen.domain.use_case.GetPodcastAudioUseCase
import com.example.itechart.home_screen.domain.use_case.GetPodcastListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPodcastListUseCase: GetPodcastListUseCase,
    private val getPodcastAudioUseCase: GetPodcastAudioUseCase,
    private val player: Player,
) : ViewModel() {

    private val _podcastAudioUri = MutableStateFlow("")
    val podcastAudioUri = _podcastAudioUri.asStateFlow()

    private val _data = MutableStateFlow<PodcastPagingData?>(null)
    val data = _data.asStateFlow()

    private val _error = MutableSharedFlow<Int>()
    val error = _error.asSharedFlow()

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    var state by mutableStateOf(ScreenState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = { isLoading ->
            state = state.copy(isLoading = isLoading)
            _loading.emit(isLoading)
        },
        onRequest = { nextPage ->
            getPodcastListUseCase(
                nextPageNumber = nextPage
            )
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            _loading.emit(false)
            state = state.copy(error = "Something went wrong")
        },
        onSuccess = { items, newKey ->
            _loading.emit(false)
            state = state.copy(
                items = state.items + items?.podcasts.orEmpty(),
                page = newKey,
                endReached = items?.podcasts?.isEmpty() ?: false
            )
        }
    )

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    init {
        //  getPodcastList()
        loadNextItems()
    }

//    fun onListenStartClick(podcastId: String) {
//        viewModelScope.launch {
//            player.play(
//                getPodcastAudioUseCase(
//                    podcastId
//                ).orEmpty()
//            )
//        }
//    }
//
//    fun onListenPauseClick() {
//        player.pause()
//    }

    fun getPodcastAudioUri(
        podcastId: String
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
               _podcastAudioUri.emit(getPodcastAudioUseCase(podcastId = podcastId).orEmpty())
            }
        }
    }

//     private fun getPodcastList() {
//        viewModelScope.launch(ioDispatcher) {
//            when(val result = getPodcastListUseCase()) {
//                is DataState.Error ->  {
//                    _error.emit(R.string.error_generic)
//                    _loading.emit(false)
//                }
//                is DataState.Success -> result.payload?.apply {
//                    _data.emit(this)
//                    _loading.emit(false)
//                }
//                is DataState.Loading -> {
//                    _loading.emit(true)
//                }
//            }
//        }
//    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<Podcast> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)