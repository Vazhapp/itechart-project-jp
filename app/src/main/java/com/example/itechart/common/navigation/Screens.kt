package com.example.itechart.common.navigation

sealed class Screens(val route: String) {
    object HomeScreen : Screens(route = "home_screen")
    object DetailsScreen : Screens(route = "details_screen")
    object FavoritesScreen : Screens(route = "favorites_screen")
}
