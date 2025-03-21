package com.example.mycity.ui.components

                    import androidx.compose.animation.animateContentSize
                    import androidx.compose.animation.core.spring
                    import androidx.compose.foundation.clickable
                    import androidx.compose.foundation.layout.*
                    import androidx.compose.foundation.shape.RoundedCornerShape
                    import androidx.compose.material.icons.Icons
                    import androidx.compose.material.icons.filled.Star
                    import androidx.compose.material3.*
                    import androidx.compose.runtime.*
                    import androidx.compose.ui.Alignment
                    import androidx.compose.ui.Modifier
                    import androidx.compose.ui.draw.clip
                    import androidx.compose.ui.unit.dp
                    import androidx.compose.ui.unit.sp
                    import androidx.navigation.NavController
                    import coil3.compose.AsyncImage

                    import com.example.mycity.data.Place

                    @Composable
                    fun PlaceCard(place: Place, navController: NavController) {
                        var expanded by remember { mutableStateOf(false) }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { navController.navigate("placeDetails/${place.name}") }
                                .animateContentSize(spring()),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column {
                                AsyncImage(
                                    model = "https://source.unsplash.com/featured/?${place.name}",
                                    contentDescription = place.name,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(180.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(text = place.name, style = MaterialTheme.typography.headlineMedium)
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(top = 4.dp)
                                    ) {
                                        Icon(Icons.Default.Star, contentDescription = "Rating", tint = MaterialTheme.colorScheme.primary)
                                        Text(text = "${place.rating} ⭐", fontSize = 16.sp, modifier = Modifier.padding(start = 4.dp))
                                    }
                                    Text(
                                        text = if (expanded) place.description else place.description.take(50) + "...",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                    TextButton(onClick = { expanded = !expanded }) {
                                        Text(if (expanded) "Show Less" else "Read More")
                                    }
                                }
                            }
                        }
                    }