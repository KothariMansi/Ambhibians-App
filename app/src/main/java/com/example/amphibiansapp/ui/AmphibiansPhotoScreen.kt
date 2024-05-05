package com.example.amphibiansapp.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibiansapp.ui.screens.AmphibiansViewModel
import com.example.amphibiansapp.ui.screens.HomeScreen

@Composable
fun AmphibiansPhotoApp() {
    Scaffold(

    ) {
        val amphibiansViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
        HomeScreen(amphibiansUiState = amphibiansViewModel.amphibiansUiState, contentPadding = it)
    }
}