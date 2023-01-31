package com.example.itechart.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.itechart.home_screen.presentation.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = Screens.DetailsScreen.route) {
            // todo Here need to implement DetailsScreen() composable
        }
    }
}