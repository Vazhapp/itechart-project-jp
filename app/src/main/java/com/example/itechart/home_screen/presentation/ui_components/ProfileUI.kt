package com.example.itechart.home_screen.presentation.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itechart.R

@Preview
@Composable
fun Profile() {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 12.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.man),
            contentDescription = "",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome Vazha!",
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.explore_podcasts),
                    fontFamily = FontFamily(Font(R.font.main_font, FontWeight.Bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
}