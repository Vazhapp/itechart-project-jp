package com.example.itechart.home_screen.presentation.uiComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.itechart.R
import com.example.itechart.common.navigation.Screens
import com.example.itechart.common.ui.WindowInfo
import com.example.itechart.home_screen.domain.model.CategoryModel
import com.example.itechart.home_screen.domain.model.Podcast
import com.example.itechart.home_screen.presentation.screens.HomeViewModel
import com.example.itechart.home_screen.presentation.screens.ScreenState
import com.example.itechart.ui.theme.DarkGray
import com.example.itechart.ui.theme.Gray
import com.example.itechart.ui.theme.LightBlue
import com.example.itechart.ui.theme.Purple
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem


@Composable
fun Categories(
    categories: List<CategoryModel>,
    podcasts: List<Podcast>,
    windowType: WindowInfo.WindowType,
    pagingState: ScreenState,
    onStartClick: (podcastId: String) -> Unit,
    onPodcastClicked: (podcastId: String) -> Unit,
//    onPauseClick: () -> Unit,
    podcastAudioUri: String,
    navController: NavController
) {
    val podcastViewModel: HomeViewModel = hiltViewModel()
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
    LazyRow(modifier = Modifier.height(100.dp)) {
        items(categories) { category ->
            CategoryItem(categoryModel = category)
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
    LazyRow(modifier = Modifier.height(230.dp)) {
        items(
            podcasts.size
        ) { index ->
            val item = podcasts[index]
            if (index >= podcasts.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                podcastViewModel.loadNextItems()
            }
            PodcastItem(podcast = item, navController = navController, podcastAudioUri, onPodcastClicked)
        }
        item {
            if (pagingState.isLoading) {
                Row(
                    modifier = Modifier
                        .height(150.dp)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator()
                }
            }
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
        text = stringResource(R.string.top_episodes_categories_title),
        fontFamily = FontFamily(Font(R.font.main_font)),
        color = Color.White,
        fontSize = 24.sp
    )
    LazyColumn(
        modifier = Modifier
            .height(300.dp)
            .padding(bottom = 16.dp)
    ) {
        items(
            podcasts.size
        ) { index ->
            val item = podcasts[index]
            if (index >= podcasts.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                podcastViewModel.loadNextItems()
            }
            // just for test now. uri will be get from viewModel
            EpisodeItem(
                podcast = item,
                windowType = windowType,
                podcastAudioUri,
                onStartClick = onStartClick
            )
        }
        item {
            if (pagingState.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
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
    podcast: Podcast,
    navController: NavController,
    podcastAudioUri: String,
    onPodcastClicked: (podcastId: String) -> Unit,
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
            .padding(12.dp)
    ) {
        Image(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    onPodcastClicked(podcast.id.orEmpty())
                  //  if (podcastAudioUri.isNotBlank()) {
                        navController.navigate(
                            Screens.DetailsScreen.passPodcastIdAndAudioUri(
                                podcastId = podcast.id.orEmpty(),
                                podcastAudioUri = "podcastAudioUri"
                            )
                        )
                   // }
                },
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

@Composable
fun EpisodeItem(
    podcast: Podcast,
    windowType: WindowInfo.WindowType,
    podcastAudioUri: String,
    onStartClick: (podcastId: String) -> Unit,
//    onPauseClick: () -> Unit
) {
    var rememberPlayerState by remember {
        mutableStateOf(true)
    }

    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val rememberImagePainter =
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(podcast.image.orEmpty()).build(),
        )

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(
                MediaItem.fromUri(
                    podcastAudioUri
                )
            )
            prepare()
        }
    }

    when (windowType) {
        WindowInfo.WindowType.Compact -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Image(
                    painter = rememberImagePainter,
                    contentDescription = "Empty",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(
                    Modifier
                        .height(130.dp)
                        .width(200.dp)
                        .align(Alignment.Bottom)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = podcast.title.orEmpty(),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.categories_font)),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.padding(top = 18.dp),
                        text = podcast.totalEpisodes.toString(),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.categories_font)),
                        color = Gray,
                    )
                }

                AnimatedVisibility(visible = rememberPlayerState) {
                    Image(
                        painter = painterResource(id = R.drawable.play_icon),
                        contentDescription = "Play",
                        modifier = Modifier
                            .height(130.dp)
                            .size(36.dp)
                            .padding(start = 8.dp)
                            .clickable {
                                rememberPlayerState = !rememberPlayerState
                                exoPlayer.play()
                            },
                        alignment = Alignment.Center
                    )
                }

                AnimatedVisibility(visible = !rememberPlayerState) {
                    Image(
                        painter = painterResource(id = R.drawable.pause_icon),
                        contentDescription = "Pause",
                        modifier = Modifier
                            .height(130.dp)
                            .size(36.dp)
                            .padding(start = 8.dp)
                            .clickable {
                                rememberPlayerState = !rememberPlayerState
                                exoPlayer.pause()
                            },
                        alignment = Alignment.Center,
                    )
                }
                DisposableEffect(
                    exoPlayer
                ) {
                    onDispose {
                        exoPlayer.release()
                    }
                }
            }
        }

        WindowInfo.WindowType.Expanded -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter,
                    contentDescription = "Empty",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(
                    Modifier
                        .height(200.dp)
                        .fillMaxWidth(0.7f),
                ) {
                    Text(
                        modifier = Modifier.padding(top = 36.dp),
                        text = podcast.title.orEmpty(),
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.categories_font)),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.padding(top = 38.dp),
                        text = podcast.publisher.orEmpty(),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.categories_font)),
                        color = Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.play_icon),
                    contentDescription = "Play or pause podcast",
                    modifier = Modifier
                        .height(180.dp)
                        .size(60.dp)
                        .padding(start = 16.dp, top = 10.dp),
                    alignment = Alignment.Center
                )
            }
        }

        WindowInfo.WindowType.Medium -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Image(
                    painter = rememberImagePainter,
                    contentDescription = "Empty",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    contentScale = ContentScale.Crop
                )
                Column(
                    Modifier
                        .height(200.dp)
                        .fillMaxWidth(0.7f)
                        .align(Alignment.Bottom)
                ) {
                    Text(
                        modifier = Modifier.padding(top = 26.dp),
                        text = podcast.title.orEmpty(),
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.categories_font)),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        modifier = Modifier.padding(top = 38.dp),
                        text = podcast.publisher.orEmpty(),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.categories_font)),
                        color = Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.play_icon),
                    contentDescription = "Play or pause podcast",
                    modifier = Modifier
                        .height(180.dp)
                        .size(62.dp)
                        .padding(start = 26.dp, top = 10.dp),
                    alignment = Alignment.Center
                )
            }
        }
    }
}