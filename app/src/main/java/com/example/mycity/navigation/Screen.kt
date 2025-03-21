package com.example.mycity.navigation

sealed class Screen(val route: String) {
    object Category : Screen("category")
    object Places : Screen("places/{category}") {
        fun createRoute(category: String) = "places/$category"
    }
    object PlaceDetailsScreen : Screen("placeDetailsScreen/{placeName}") {
        fun createRoute(placeName: String) = "placeDetailsScreen/$placeName"
    }
    object Favorites : Screen("favorites")
}
