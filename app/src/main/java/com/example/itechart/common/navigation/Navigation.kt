package com.example.itechart.common.navigation

import android.util.Log.d
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.itechart.details_screen.presentation.screens.DetailsScreen
import com.example.itechart.home_screen.presentation.screens.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(hiltViewModel(), navController = navController)
        }
        composable(
            route = Screens.DetailsScreen.route,
            arguments = listOf(
                navArgument(PODCAST_ID_ARGUMENT_KEY) {
                    type = NavType.StringType
                },
                navArgument(PODCAST_AUDIO_URI_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            DetailsScreen(
                podcastId = it.arguments?.getString(PODCAST_ID_ARGUMENT_KEY).orEmpty(),
              //  podcastAudioUri = it.arguments?.getString(PODCAST_AUDIO_URI_ARGUMENT_KEY).orEmpty(),
                hiltViewModel(),
            )
        }
    }
}