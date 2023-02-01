package com.example.itechart.home_screen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.itechart.R
import com.example.itechart.home_screen.presentation.ui_components.Profile
import com.example.itechart.home_screen.presentation.ui_components.Search

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.main_background),
            contentDescription = "Main background",
            contentScale = ContentScale.FillBounds
        )
        Row(modifier = Modifier.wrapContentWidth()) {
            Profile()
            Search()
        }
    }
}