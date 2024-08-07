package com.ae.cityfinder.model

data class City(
    val country: String,
    val name: String,
    val _id: Int,
    val coord: Coord,
    // Reference to the next city in the linked list
    var nextCity: City? = null
)

data class Coord(
    val lon: Double,
    val lat: Double
)
