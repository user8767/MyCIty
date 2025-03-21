package com.example.mycity.data

import com.example.mycity.R

object SampleData {
    val categories = listOf("Restaurants", "Parks", "Museums")

    val places = listOf(
        Place("The Spice House", "Restaurants", "A famous restaurant known for its spicy food.", 4.5f, R.drawable.restaurant),
        Place("Green Valley Park", "Parks", "A peaceful park with beautiful gardens.", 4.2f, R.drawable.park),
        Place("History Museum", "Museums", "A museum showcasing historical artifacts.", 4.7f, R.drawable.museum)
    )
}
