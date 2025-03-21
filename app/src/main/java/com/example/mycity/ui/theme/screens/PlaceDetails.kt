package com.example.mycity.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycity.data.SampleData
import com.example.mycity.viewmodel.MyCityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetails(navController: NavController, placeName: String, viewModel: MyCityViewModel) {
    val context = LocalContext.current
    val place = SampleData.places.find { it.name == placeName }

    place?.let {
        var isFavorite by remember { mutableStateOf(viewModel.isFavorite(it)) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(it.name) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        // Favorite Button
                        IconButton(onClick = {
                            isFavorite = if (isFavorite) {
                                viewModel.removeFromFavorites(it)
                                false
                            } else {
                                viewModel.addToFavorites(it)
                                true
                            }
                        }) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorite"
                            )
                        }

                        // Share Button
                        IconButton(onClick = {
                            val shareText = "Check out ${it.name}! Rated ${it.rating} ⭐. ${it.description}"
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share via"))
                        }) {
                            Icon(Icons.Default.Share, contentDescription = "Share")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = it.imageResId),
                    contentDescription = it.name,
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Rating: ${it.rating} ⭐", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it.description, style = MaterialTheme.typography.bodyLarge)
            }
        }
    } ?: Text("Place not found", modifier = Modifier.padding(16.dp))
}
