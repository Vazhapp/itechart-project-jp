package com.example.itechart.details_screen.presentation.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.itechart.R

@Composable
fun MediaController(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.rewind_10_second),
            contentDescription = "",
            modifier.size(50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.play),
            contentDescription = "",
            modifier.size(50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.forward_10_second),
            contentDescription = "",
            modifier.size(50.dp)
        )
    }
}