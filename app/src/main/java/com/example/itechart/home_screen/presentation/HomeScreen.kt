package com.example.itechart.home_screen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.itechart.R
import com.example.itechart.common.ui.ShimmerListItem
import com.example.itechart.common.ui.rememberWindowInfo
import com.example.itechart.home_screen.domain.model.CategoryModel
import com.example.itechart.home_screen.presentation.ui_components.Categories
import com.example.itechart.home_screen.presentation.ui_components.ExpandableSearchView
import com.example.itechart.home_screen.presentation.ui_components.Profile

@Composable
fun HomeScreen() {
    val podcastsViewModel: PodcastsViewModel = hiltViewModel()
    val rememberWindowInfo = rememberWindowInfo()
    var collapseSearchBarState by remember { mutableStateOf(false) }

    Image(
        modifier = Modifier
            .fillMaxSize()
            .blur(radius = 20.dp),
        painter = painterResource(id = R.drawable.main_background),
        contentDescription = "Main background",
        contentScale = ContentScale.FillBounds
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                collapseSearchBarState = true
            },
    ) {
        item {
            Row(modifier = Modifier.wrapContentWidth()) {
                Profile()
                ExpandableSearchView(
                    "",
                    {},
                    {},
                    Modifier,
                    expandedInitially = false,
                    Color.White,
                    collapseSearchBar = collapseSearchBarState
                )
                collapseSearchBarState = false
            }
            ShimmerListItem(
                isLoading = podcastsViewModel.loading.collectAsState().value,
                contentAfterLoading = {
                    val podcastList = podcastsViewModel.data.collectAsState()
                    podcastList.value?.podcasts.let {
                        Categories(
                            categories = generateDummyCategories(),
                            podcasts = it.orEmpty(),
                            windowType = rememberWindowInfo.screenWidthInfo
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