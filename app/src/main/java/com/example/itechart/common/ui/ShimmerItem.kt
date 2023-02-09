package com.example.itechart.common.ui

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itechart.R

private const val STANDARD_LOADING_ITEMS_COUNT = 3

@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        Column(modifier = Modifier.fillMaxSize()) {
            CategoryLoadingItem(modifier = modifier, itemsCount = STANDARD_LOADING_ITEMS_COUNT)
            NewAndNoteworthyLoadingItem(modifier = modifier, itemsCount = STANDARD_LOADING_ITEMS_COUNT)
            LoadingTopEpisodesItem(modifier = modifier, itemsCount = STANDARD_LOADING_ITEMS_COUNT)
        }
    } else {
        contentAfterLoading()
    }
}

@Composable
fun CategoryLoadingItem(
    modifier: Modifier,
    itemsCount: Int
) {
    Text(
        modifier = modifier.padding(start = 4.dp),
        text = stringResource(R.string.new_podcasts_categories_title),
        fontFamily = FontFamily(Font(R.font.main_font)),
        color = Color.White,
        fontSize = 24.sp
    )
    LazyRow(modifier = modifier.height(150.dp)) {
        items(itemsCount) {
            Box(
                modifier = modifier
                    .height(98.dp)
                    .width(165.dp)
                    .padding(start = 10.dp, top = 10.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .shimmerEffect()
            )
        }
    }
}

@Composable
fun NewAndNoteworthyLoadingItem(
    modifier: Modifier,
    itemsCount: Int
) {
    Text(
        modifier = modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 10.dp
            ),
        text = stringResource(R.string.new_noteworthy_categories_title),
        fontFamily = FontFamily(Font(R.font.main_font)),
        color = Color.White,
        fontSize = 24.sp
    )
    LazyRow(modifier = modifier.wrapContentHeight(), contentPadding = PaddingValues(5.dp)) {
        items(itemsCount) {
            Column(modifier = Modifier.size(200.dp)) {

                Box(
                    modifier = modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = modifier.height(4.dp))
                Box(
                    modifier = modifier
                        .fillMaxWidth(0.4f)
                        .height(14.dp)
                        .padding(start = 4.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = modifier.height(4.dp))
                Box(
                    modifier = modifier
                        .fillMaxWidth(0.2f)
                        .height(14.dp)
                        .padding(start = 4.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}

@Composable
fun LoadingTopEpisodesItem(
    modifier: Modifier,
    itemsCount: Int
) {
    Text(
        modifier = modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 10.dp
            ),
        text = stringResource(R.string.top_episodes_categories_title),
        fontFamily = FontFamily(Font(R.font.main_font)),
        color = Color.White,
        fontSize = 24.sp
    )
    LazyColumn(modifier = modifier.height(300.dp), contentPadding = PaddingValues(6.dp)) {
        items(itemsCount) {
            Row(
                modifier = modifier
                    .wrapContentHeight()
                    .padding(6.dp)
            ) {
                Box(
                    modifier = modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = modifier.width(16.dp))
                Column(
                    modifier = modifier.weight(1f)
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth(0.6f)
                            .height(14.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Box(
                        modifier = modifier
                            .fillMaxWidth(0.4f)
                            .height(14.dp)
                            .shimmerEffect()
                    )
                }
            }
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF424242),
                Color(0xFF5A5A5A),
                Color(0xFF424242),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}