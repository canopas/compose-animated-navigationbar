package com.example.bottombar

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bottombar.components.ActiveTabIndicator
import com.example.bottombar.utils.IndicatorDirection
import com.example.bottombar.utils.conditional

val DEFAULT_INDICATOR_HEIGHT = 4.dp

@Composable
fun BottomBarWithTabIndicator(
    modifier: Modifier = Modifier,
    bottomBarHeight: Dp = 64.dp,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    selectedItem: Int? = null,
    itemSize: Int? = null,
    indicatorColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    indicatorHeight: Dp = DEFAULT_INDICATOR_HEIGHT,
    animationSpec: AnimationSpec<Dp> = spring(
        dampingRatio = 1f,
        stiffness = Spring.StiffnessMediumLow
    ),
    indicatorDirection: IndicatorDirection = IndicatorDirection.TOP,
    indicatorShape: RoundedCornerShape = RoundedCornerShape(25.dp),
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        modifier = modifier
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dp(bottomBarHeight / (bottomBarHeight / 16)))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(bottomBarHeight)
                    .selectableGroup(),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = content
            )

            selectedItem?.let {
                itemSize?.let {
                    val maxWidth = this.maxWidth
                    val indicatorOffset: Dp by animateDpAsState(
                        targetValue = (maxWidth / itemSize) * selectedItem,
                        animationSpec = animationSpec,
                        label = "tab-indicator"
                    )

                    ActiveTabIndicator(
                        indicatorOffset = indicatorOffset,
                        arraySize = itemSize,
                        indicatorHeight = indicatorHeight,
                        indicatorColor = indicatorColor,
                        modifier = Modifier
                            .conditional(indicatorDirection == IndicatorDirection.TOP) {
                                align(Alignment.TopStart)
                            }
                            .conditional(indicatorDirection == IndicatorDirection.BOTTOM) {
                                align(Alignment.BottomStart)
                            },
                        indicatorShape = indicatorShape
                    )
                }
            }
        }
    }
}