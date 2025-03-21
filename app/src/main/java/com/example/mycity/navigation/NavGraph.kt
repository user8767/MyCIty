package com.example.mycity.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mycity.ui.screens.*
import com.example.mycity.viewmodel.MyCityViewModel
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Category : Screen("category")
    object Favorites : Screen("favorites")

    object Places : Screen("places/{category}") {
        fun createRoute(category: String) = "places/${encode(category)}"
        fun fromArgs(entry: NavBackStackEntry) = decode(entry.arguments?.getString("category")!!)
    }

    object PlaceDetails : Screen("place_details/{placeName}") {
        fun createRoute(placeName: String) = "place_details/${encode(placeName)}"
        fun fromArgs(entry: NavBackStackEntry) = decode(entry.arguments?.getString("placeName")!!)
    }

    companion object {
        private fun encode(value: String) = URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
        private fun decode(value: String) = URLDecoder.decode(value, StandardCharsets.UTF_8.toString())
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController, viewModel: MyCityViewModel) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route,
            enterTransition = { slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { -it }) }
        ) { HomeScreen(navController) }

        composable(
            route = Screen.Category.route,
            enterTransition = { slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { -it }) }
        ) { CategoryScreen(navController) }

        composable(
            route = Screen.Favorites.route,
            enterTransition = { slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { -it }) }
        ) { FavoritesScreen(navController, viewModel) }

        composable(
            route = Screen.Places.route,
            arguments = listOf(navArgument("category") { type = NavType.StringType }),
            enterTransition = { slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { -it }) }
        ) { backStackEntry ->
            val category = Screen.Places.fromArgs(backStackEntry)
            PlacesScreen(navController, category, viewModel)
        }

        composable(
            route = Screen.PlaceDetails.route,
            arguments = listOf(navArgument("placeName") { type = NavType.StringType }),
            enterTransition = { slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it }) },
            exitTransition = { slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { -it }) }
        ) { backStackEntry ->
            val placeName = Screen.PlaceDetails.fromArgs(backStackEntry)
            PlaceDetails(navController, placeName, viewModel)
        }
    }
}
