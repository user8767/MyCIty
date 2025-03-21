package com.example.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mycity.ui.navigation.NavGraph
import com.example.mycity.ui.theme.MyCityTheme
import com.example.mycity.viewmodel.MyCityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCityTheme {
                val navController = rememberNavController()
                val viewModel: MyCityViewModel = viewModel()
                NavGraph(navController, viewModel)
            }

    }}}



