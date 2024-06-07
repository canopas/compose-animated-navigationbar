package com.example.bottombar.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp

internal fun Modifier.conditional(
    condition: Boolean,
    orElse: (@Composable Modifier.() -> Modifier)? = null,
    modifier: @Composable Modifier.() -> Modifier,
): Modifier =
    composed {
        if (condition) {
            modifier.invoke(this)
        } else {
            orElse?.invoke(this) ?: this
        }
    }

internal fun Modifier.customWormTransition(
    offset: Dp,
    indicatorColor: Color,
    itemWidth: Dp,
) = composed {
    drawWithContent {
        val distance = itemWidth.roundToPx()
        val scrollPosition = (offset.toPx().div(distance))
        val wormOffset = (scrollPosition % 1) * 2

        val xPos = scrollPosition.toInt() * distance
        val head = xPos + distance * 0f.coerceAtLeast(wormOffset - 1)
        val tail = xPos + size.width + distance * 1f.coerceAtMost(wormOffset)

        val worm =
            RoundRect(
                head,
                0f,
                tail,
                size.height,
                CornerRadius(50f),
            )

        val path = Path().apply { addRoundRect(worm) }

        // Draw the worm shape on top of the content
        drawPath(path = path, color = indicatorColor)
    }
}
