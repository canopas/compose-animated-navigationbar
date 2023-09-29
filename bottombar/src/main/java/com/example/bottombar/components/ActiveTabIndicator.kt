package com.example.bottombar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
@Composable
internal fun ActiveTabIndicator(
    indicatorOffset: Dp,
    arraySize: Int,
    indicatorColor: Color,
    indicatorHeight: Dp,
    modifier: Modifier,
    indicatorShape: RoundedCornerShape
) {
    Box(
        modifier = modifier
            .height(indicatorHeight)
            .fillMaxWidth(1f / arraySize)
            .offset(
                x = indicatorOffset
            )
            .clip(shape = indicatorShape)
            .background(indicatorColor, shape = indicatorShape)
    )
}
