package com.example.itechart.home_screen.presentation.ui_components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itechart.R
import com.example.itechart.home_screen.domain.model.CategoryModel
import com.example.itechart.ui.theme.LightBlue
import com.example.itechart.ui.theme.Purple


@Composable
fun CategoryList(categories: List<CategoryModel>) {
    Column(modifier = Modifier.wrapContentWidth().wrapContentHeight().padding(16.dp)) {
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