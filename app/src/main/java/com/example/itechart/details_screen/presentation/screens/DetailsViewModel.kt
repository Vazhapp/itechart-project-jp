package com.example.itechart.details_screen.presentation.screens

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itechart.common.Resource
import com.example.itechart.details_screen.domain.use_case.GetPodcastDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailsScreenUiState(
    val isLoading: Boolean = false,
    val podcastAudioUri: String = "",
)

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPodcastAudioUseCase: GetPodcastDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailsScreenUiState())
    val uiState: StateFlow<DetailsScreenUiState> = _uiState.asStateFlow()

    fun getPodcastAudioUri(
        podcastId: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isLoading = true)
            }
            when (val result = getPodcastAudioUseCase(podcastId)) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            podcastAudioUri = result.data!!.episodes!![0]!!.audio ?: ""
                        )
                    }
                }

                is Resource.Error -> {
                    d("Error Appear", "E.")
                }
            }
        }
    }
}