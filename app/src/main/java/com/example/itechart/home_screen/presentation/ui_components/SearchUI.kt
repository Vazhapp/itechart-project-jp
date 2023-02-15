package com.example.itechart.home_screen.presentation.ui_components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.itechart.R
import com.example.itechart.ui.theme.DarkGray

@Composable
fun ExpandableSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    modifier: Modifier = Modifier,
    expandedInitially: Boolean = false,
    tint: Color = MaterialTheme.colors.onPrimary,
    collapseSearchBar: Boolean,
) {
    val (expanded, onExpandedChanged) = remember {
        mutableStateOf(expandedInitially)
    }
    Crossfade(targetState = expanded) { isSearchFieldVisible ->
        when (isSearchFieldVisible) {
            true -> ExpandedSearchView(
                searchDisplay = searchDisplay,
                onSearchDisplayChanged = onSearchDisplayChanged,
                onSearchDisplayClosed = onSearchDisplayClosed,
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint,
                collapseSearchBar = collapseSearchBar
            )
            false -> CollapsedSearchView(onExpandedChanged = onExpandedChanged)
        }
    }
}

@Composable
fun CollapsedSearchView(
    onExpandedChanged: (Boolean) -> Unit,
) {
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
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.LightGray,
                            Color.Gray
                        )
                    ),
                    shape = RoundedCornerShape(
                        40.dp
                    )
                ),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(onClick = { onExpandedChanged(true) }) {
                SearchIcon(iconTint = Color.White)
            }
        }
    }
}

@Composable
fun SearchIcon(iconTint: Color) {
    Icon(
        painter = painterResource(id = R.drawable.search),
        contentDescription = "search icon",
        tint = iconTint,
        modifier = Modifier
            .padding(top = 12.dp, end = 5.dp)
            .size(44.dp)
            .height(100.dp)
            .padding(2.dp),
    )
}

@Composable
fun ExpandedSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = DarkGray,
    collapseSearchBar: Boolean,
) {
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    SideEffect {
        textFieldFocusRequester.requestFocus()
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    Row(
        modifier = modifier.fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(70.dp)
                .border(
                    width = 1.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.LightGray,
                            Color.Gray
                        )
                    ),
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
            TextField(
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    onSearchDisplayChanged(it.text)
                },
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .focusRequester(textFieldFocusRequester)
                    .clip(RoundedCornerShape(30.dp))
                    .align(Alignment.Center)
                    .onFocusChanged {
                        if (it.isFocused) {
                            onExpandedChanged(true)
                        } else {
                            onExpandedChanged(false)
                        }
                    },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focusManager.clearFocus()
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(textColor = Color.White)
            )
            if (collapseSearchBar) {
                onExpandedChanged(false)
            }
        }
    }
}