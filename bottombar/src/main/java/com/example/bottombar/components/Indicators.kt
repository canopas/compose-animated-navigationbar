package com.example.bottombar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.canopas.lib.bottombar.R
import com.example.bottombar.utils.customWormTransition

@Composable
internal fun LineIndicator(
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

@Composable
internal fun DotIndicator(
    indicatorOffset: Dp,
    arraySize: Int,
    indicatorColor: Color,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(1f / arraySize)
            .offset(
                x = indicatorOffset
            )
    ) {
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_dot_indicator),
                contentDescription = "",
                tint = indicatorColor,
                modifier = Modifier
                    .size(8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
internal fun WormIndicator(
    indicatorOffset: Dp,
    indicatorColor: Color,
    modifier: Modifier,
    itemWidth: Dp
) {
    Box(
        modifier = modifier
            .width(itemWidth)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 4.dp)
                .customWormTransition(indicatorOffset, indicatorColor, itemWidth)
                .size(8.dp)
        )
    }
}

@Composable
internal fun FilledIndicator(
    indicatorOffset: Dp,
    arraySize: Int,
    indicatorColor: Color,
    modifier: Modifier,
    indicatorShape: RoundedCornerShape
) {
    Box(
        modifier = modifier
            .fillMaxWidth(1f / arraySize)
            .offset(
                x = indicatorOffset
            )
            .padding(vertical = 8.dp)
            .clip(indicatorShape)
            .background(indicatorColor, shape = indicatorShape)
    )
}