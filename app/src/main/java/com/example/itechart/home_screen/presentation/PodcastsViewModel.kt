package com.example.itechart.home_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itechart.R
import com.example.itechart.common.DataState
import com.example.itechart.home_screen.domain.model.PodcastPagingData
import com.example.itechart.home_screen.domain.use_case.GetPodcastListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastsViewModel @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val getPodcastListUseCase: GetPodcastListUseCase,
) : ViewModel() {

    private val _data = MutableStateFlow<PodcastPagingData?>(null)
    val data = _data.asStateFlow()

    private val _error = MutableSharedFlow<Int>()
    val error = _error.asSharedFlow()

    init {
        getPodcastList()
    }

     private fun getPodcastList() {
        viewModelScope.launch(ioDispatcher) {
            when(val result = getPodcastListUseCase()) {
                is DataState.Error -> _error.emit(R.string.error_generic)
                is DataState.Success -> result.payload?.apply {
                    _data.emit(this)
                }
                else -> { }
            }
        }
    }
}