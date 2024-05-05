package com.example.amphibiansapp.data

import com.example.amphibiansapp.network.AmphibiansApiService
import com.example.amphibiansapp.network.AmphibiansPhoto

interface AmphibiansPhotoRepository {
    suspend fun getPhotos(): List<AmphibiansPhoto>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
): AmphibiansPhotoRepository {
    override suspend fun getPhotos(): List<AmphibiansPhoto> {
        return amphibiansApiService.getPhotos()
    }

}