package com.example.mycity.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavController
import com.example.mycity.data.SampleData
import com.example.mycity.ui.navigation.Screen
import com.example.mycity.viewmodel.MyCityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesScreen(navController: NavController, category: String, viewModel: MyCityViewModel) {
    var sortedDescending by remember { mutableStateOf(true) }
    val places = remember(sortedDescending) {
        SampleData.places.filter { it.category == category }
            .sortedByDescending { if (sortedDescending) it.rating else -it.rating }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("$category Places", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    val rotation by animateFloatAsState(if (sortedDescending) 0f else 180f)
                    IconButton(onClick = { sortedDescending = !sortedDescending }) {
                        Icon(
                            Icons.Default.Place,
                            contentDescription = "Sort",
                            modifier = Modifier.graphicsLayer {
                                rotationZ = rotation
                            }
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .animateContentSize()
        ) {
            items(places) { place ->
                PlaceItem(place.name, place.rating) {
                    navController.navigate(Screen.PlaceDetails.createRoute(place.name))
                }
            }
        }
    }
}

@Composable
fun PlaceItem(name: String, rating: Float, onClick: () -> Unit) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { isVisible = true }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 })
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable(onClick = onClick),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = name, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "Rating: $rating ⭐",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
