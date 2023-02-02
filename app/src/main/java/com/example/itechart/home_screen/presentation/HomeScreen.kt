package com.example.itechart.home_screen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.itechart.R
import com.example.itechart.home_screen.domain.model.CategoryModel
import com.example.itechart.home_screen.presentation.ui_components.Categories
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
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp)) {
            Categories(
                categories = generateDummyCategories()
            )
        }
    }
}

fun generateDummyCategories(): List<CategoryModel> =
    listOf(
        CategoryModel("Trending"),
        CategoryModel("Top Rated"),
        CategoryModel("Top Viewed"),
        CategoryModel("Most Recent"),
        CategoryModel("Paid"),
    )