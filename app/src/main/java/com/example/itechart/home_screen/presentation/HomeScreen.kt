package com.example.itechart.home_screen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.itechart.R
import com.example.itechart.common.ui.ShimmerListItem
import com.example.itechart.home_screen.domain.model.CategoryModel
import com.example.itechart.home_screen.presentation.ui_components.Categories
import com.example.itechart.home_screen.presentation.ui_components.Profile
import com.example.itechart.home_screen.presentation.ui_components.Search

@Composable
fun HomeScreen() {
    val podcastsViewModel: PodcastsViewModel = hiltViewModel()

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.main_background),
        contentDescription = "Main background",
        contentScale = ContentScale.FillBounds
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Row(modifier = Modifier.wrapContentWidth()) {
                Profile()
                Search()
            }
            ShimmerListItem(
                isLoading = podcastsViewModel.loading.collectAsState().value,
                contentAfterLoading = {
                    val podcastList = podcastsViewModel.data.collectAsState()
                    podcastList.value?.podcasts.let {
                        Categories(
                            categories = generateDummyCategories(),
                            podcasts = it.orEmpty()
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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