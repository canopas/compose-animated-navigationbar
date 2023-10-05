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
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bottombar.R

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
                .customWormTransition(indicatorOffset, indicatorColor, itemWidth)
                .size(8.dp)
                .padding(bottom = 4.dp)
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

fun Modifier.customWormTransition(
    offset: Dp,
    indicatorColor: Color,
    itemWidth: Dp
) = composed {
    drawWithContent {
        val distance = itemWidth.roundToPx()
        val scrollPosition = (offset.toPx().div(distance))
        val wormOffset = (scrollPosition % 1) * 2

        val xPos = scrollPosition.toInt() * distance
        val head = xPos + distance * 0f.coerceAtLeast(wormOffset - 1)
        val tail = xPos + size.width + distance * 1f.coerceAtMost(wormOffset)

        val worm = RoundRect(
            head, 0f, tail, size.height,
            CornerRadius(50f)
        )

        val path = Path().apply { addRoundRect(worm) }

        // Draw the worm shape on top of the content
        drawPath(path = path, color = indicatorColor)
    }
}