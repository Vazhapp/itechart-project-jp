package com.example.itechart.home_screen.presentation.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itechart.R

@Preview
@Composable
fun Search() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        Box(
            modifier = Modifier
                .width(57.dp)
                .height(70.dp)
                .border(
                    width = 1.dp,
                    brush = Brush.verticalGradient(colors = listOf(Color.LightGray, Color.Gray)),
                    shape = RoundedCornerShape(
                        40.dp
                    )
                ),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 12.dp, end = 5.dp)
                    .size(44.dp)
                    .height(100.dp)
                    .padding(2.dp),
                painter = painterResource(id = R.drawable.search),
                contentDescription = "",
                alignment = Alignment.Center
            )
        }
    }
}