package com.example.mycity.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycity.data.SampleData
import com.example.mycity.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("MyCity - Categories") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            SampleData.categories.forEach { category ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate(Screen.Places.createRoute(category)) }
                ) {
                    Text(
                        text = category,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }
}
