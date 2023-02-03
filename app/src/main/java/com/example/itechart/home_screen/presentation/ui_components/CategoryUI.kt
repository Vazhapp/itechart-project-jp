package com.example.itechart.home_screen.presentation.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.itechart.R
import com.example.itechart.home_screen.domain.model.CategoryModel
import com.example.itechart.home_screen.domain.model.PodcastPagingData
import com.example.itechart.home_screen.domain.model.Podcast
import com.example.itechart.ui.theme.DarkGray
import com.example.itechart.ui.theme.LightBlue
import com.example.itechart.ui.theme.Purple
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun Categories(categories: List<CategoryModel>, podcasts: List<Podcast>) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.new_podcasts_categories_title),
            fontFamily = FontFamily(Font(R.font.main_font)),
            color = Color.White,
            fontSize = 24.sp
        )
    }
    LazyRow {
        items(categories) { category ->
            CategoryItem(
                categoryModel = category
            )
        }
    }
    LazyRow {
        items(
            podcasts
        ) { podcast ->
            PodcastItem(podcast = podcast)
        }
    }
}

@Composable
fun CategoryItem(categoryModel: CategoryModel) {
    Box(
        modifier = Modifier
            .height(97.dp)
            .width(165.dp)
            .padding(start = 10.dp, top = 10.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(
                brush = Brush.horizontalGradient(
                    listOf(LightBlue, Purple)
                )
            ),
    ) {
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .align(Alignment.Center),
            text = categoryModel.title,
            fontSize = 18.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.categories_font)),
        )
    }
}

@Composable
fun PodcastItem(
    podcast: Podcast
) {
    val rememberImagePainter =
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(podcast.image.orEmpty()).build()
        )
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(16.dp)
            .background(Color.Black)
    ) {
        Image(
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(20.dp)),
            painter = rememberImagePainter,
            contentDescription = "Empty"
        )
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(10.dp)
        ) {
            Column {
                Text(
                    text = podcast.title.orEmpty(),
                    fontFamily = FontFamily(Font(R.font.categories_font)),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Text(
                    text = podcast.publisher.orEmpty(),
                    fontFamily = FontFamily(Font(R.font.categories_font)),
                    fontSize = 14.sp,
                    color = DarkGray
                )
            }
        }
    }
}