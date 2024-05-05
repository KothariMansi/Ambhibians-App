package com.example.amphibiansapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansapp.network.AmphibiansPhoto
import com.example.amphibiansapp.ui.theme.AmphibiansAppTheme

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    contentPadding: PaddingValues,
    modifier : Modifier = Modifier
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Success -> AmphibiansListScreen(amphibiansPhotoList = amphibiansUiState.photos)
        AmphibiansUiState.Error -> {}
        AmphibiansUiState.Loading -> {}
    }
    
}

@Composable
fun AmphibiansListScreen(
    amphibiansPhotoList: List<AmphibiansPhoto>
) {
    LazyColumn {
        items(amphibiansPhotoList) {
            AmphibiansCard(amphibiansPhoto = it)
        }
    }
    
}

@Composable
fun AmphibiansCard(
    amphibiansPhoto: AmphibiansPhoto,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = modifier.padding(8.dp)
            ) {
                Text(
                    text = amphibiansPhoto.name,
                    style = MaterialTheme.typography.titleLarge,

                )
                Text(
                    text = "(" + amphibiansPhoto.type + ")",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
                .data(amphibiansPhoto.imgSrc)
                .crossfade(true)
                .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = modifier.fillMaxWidth()
            )
            Text(
                text = amphibiansPhoto.description,
                modifier = modifier.padding(8.dp)
            )

        }

    }
}

@Preview
@Composable
fun AmphibiansCardScreen() {
    AmphibiansAppTheme {
        val mockData = List(10) {AmphibiansPhoto("Amhibians", "Todo", "pata ni", "")}
        AmphibiansListScreen(amphibiansPhotoList = mockData)
    }
}
