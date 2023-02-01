package com.example.itechart.home_screen.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.itechart.home_screen.presentation.ui_components.Profile
import com.example.itechart.home_screen.presentation.ui_components.Search

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.wrapContentWidth()) {
            Profile()
            Search()
        }
    }
}