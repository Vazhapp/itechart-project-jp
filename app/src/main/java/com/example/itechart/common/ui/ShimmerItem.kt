package com.example.itechart.common.ui

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itechart.R
import com.example.itechart.home_screen.presentation.ui_components.EpisodeItem
import com.example.itechart.ui.theme.DarkGray
import com.example.itechart.ui.theme.Gray

@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading) {

        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = modifier.padding(start = 4.dp),
                text = stringResource(R.string.new_podcasts_categories_title),
                fontFamily = FontFamily(Font(R.font.main_font)),
                color = Color.White,
                fontSize = 24.sp
            )
            LazyRow(modifier = Modifier.height(150.dp)) {
                items(3) {
                    Box(
                        modifier = Modifier
                            .height(97.dp)
                            .width(165.dp)
                            .padding(start = 10.dp, top = 10.dp)
                            .clip(RoundedCornerShape(22.dp))
                            .shimmerEffect()
                    )
                }
            }
            Text(
                modifier = Modifier
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
            LazyRow(modifier = Modifier.wrapContentHeight(), contentPadding = PaddingValues(5.dp)) {
                items(3) {
                    Column(modifier = Modifier.size(200.dp)) {

                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .height(14.dp)
                                .padding(start = 4.dp)
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.2f)
                                .height(14.dp)
                                .padding(start = 4.dp)
                                .shimmerEffect()
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
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
            LazyColumn(modifier = Modifier.height(300.dp), contentPadding = PaddingValues(6.dp)) {
                items(3) {
                    Row(modifier = Modifier.wrapContentHeight().padding(6.dp)) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .height(14.dp)
                                    .shimmerEffect()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.4f)
                                    .height(14.dp)
                                    .shimmerEffect()
                            )
                        }
                    }
                }
            }
        }


    } else {
          contentAfterLoading()
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