package com.example.amphibiansapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibiansapp.AmphibiansPhotoApplication
import com.example.amphibiansapp.data.AmphibiansPhotoRepository
import com.example.amphibiansapp.network.AmphibiansPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val photos: List<AmphibiansPhoto>): AmphibiansUiState
    object Loading: AmphibiansUiState
    object Error : AmphibiansUiState
}



class AmphibiansViewModel(
    private val amphibiansRepository: AmphibiansPhotoRepository
): ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibiansPhoto()
    }

     private fun getAmphibiansPhoto() {
        viewModelScope.launch {
            amphibiansUiState = try {
               AmphibiansUiState.Success( amphibiansRepository.getPhotos())
            } catch (e: IOException) {
                AmphibiansUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansPhotoApplication)
                val amphibiansRepository = application.container.amphibiansPhotoRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }

}