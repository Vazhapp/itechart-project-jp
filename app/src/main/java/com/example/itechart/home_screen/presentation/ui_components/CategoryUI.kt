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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.itechart.R
import com.example.itechart.home_screen.domain.model.CategoryModel
import com.example.itechart.home_screen.domain.model.Podcast
import com.example.itechart.ui.theme.*


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
    Text(
        modifier = Modifier
            .padding(
                top = 36.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 10.dp
            ),
        text = stringResource(R.string.new_noteworthy_categories_title),
        fontFamily = FontFamily(Font(R.font.main_font)),
        color = Color.White,
        fontSize = 24.sp
    )
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
                .data(podcast.image.orEmpty()).build(),
        )
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(20.dp)),
            painter = rememberImagePainter,
            contentDescription = "Empty",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(4.dp)
        ) {
            Column {
                Text(
                    text = podcast.title.orEmpty(),
                    fontFamily = FontFamily(Font(R.font.categories_font)),
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = podcast.publisher.orEmpty(),
                    fontFamily = FontFamily(Font(R.font.categories_font)),
                    fontSize = 14.sp,
                    color = DarkGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun EpisodeItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_background),
            contentDescription = "Empty",
            modifier = Modifier
                .size(120.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(30.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            Modifier
                .height(120.dp)
                .wrapContentWidth()
                .align(Alignment.Bottom)
        ) {
            Text(
                modifier = Modifier.padding(top = 26.dp),
                text = "Vazha Kentchiashvili's speak",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.categories_font)),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.padding(top = 28.dp),
                text = "16 August, 2023",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.categories_font)),
                color = Gray,
            )
        }
    }
}