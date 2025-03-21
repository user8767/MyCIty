package com.example.mycity.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mycity.data.Place

class MyCityViewModel : ViewModel() {
    val favoritePlaces = mutableStateListOf<Place>()

    fun addToFavorites(place: Place) {
        if (!favoritePlaces.contains(place)) {
            favoritePlaces.add(place)
        }
    }

    fun removeFromFavorites(place: Place) {
        favoritePlaces.remove(place)
    }

    fun isFavorite(place: Place) = favoritePlaces.contains(place)
}
