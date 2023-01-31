package com.example.itechart.home_screen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.itechart.home_screen.presentation.ui_components.Profile

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Profile()
    }
}