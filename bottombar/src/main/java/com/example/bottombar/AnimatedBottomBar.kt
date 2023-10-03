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
import com.example.bottombar.components.DotIndicator
import com.example.bottombar.components.FilledIndicator
import com.example.bottombar.components.LineIndicator
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombar.utils.conditional

val DEFAULT_INDICATOR_HEIGHT = 4.dp

/**
 * A composable function that creates layout for bottom navigation & indicator over it.
 *
 *@param [modifier] Modifier to be applied to the navigation bar.
 *@param [bottomBarHeight] The default height of the bottom bar.
 *@param [containerColor] The color of the navigation bar/container.
 *@param [contentColor] The color of the content.
 *@param [selectedItem] The index of currently selected item.
 *@param [itemSize] The count of items that will be shown in bottom bar.
 *@param [indicatorStyle] The type of indicator that need to be shown. Select from [IndicatorStyle]
 *@param [indicatorColor] The color to be applied on the indicator.
 *@param [indicatorHeight] The height of the indicator - `applicable only for line indicator`.
 *@param [animationSpec] The animation that will be applied to tab indicators when offset changes.
 *@param [indicatorDirection] The direction where indicator need to be placed. Select from [IndicatorDirection]
 *@param [indicatorShape] The shape to be applied on the indicator.
 *@param [content] The composable content of the navigation bar.
 */
@Composable
fun AnimatedBottomBar(
    modifier: Modifier = Modifier,
    bottomBarHeight: Dp = 64.dp,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    selectedItem: Int? = null,
    itemSize: Int? = null,
    indicatorStyle: IndicatorStyle = IndicatorStyle.NONE,
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
            selectedItem?.let {
                itemSize?.let {
                    val maxWidth = this.maxWidth
                    val indicatorOffset: Dp by animateDpAsState(
                        targetValue = (maxWidth / itemSize) * selectedItem,
                        animationSpec = animationSpec,
                        label = "indicator"
                    )

                    when (indicatorStyle) {
                        IndicatorStyle.NONE -> {}
                        IndicatorStyle.DOT -> {
                            DotIndicator(
                                indicatorOffset = indicatorOffset,
                                arraySize = itemSize,
                                indicatorColor = Color.White,
                                modifier = Modifier
                                    .height(bottomBarHeight)
                            )
                        }

                        IndicatorStyle.LINE -> {
                            LineIndicator(
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

                        IndicatorStyle.FILLED -> {
                            FilledIndicator(
                                indicatorOffset = indicatorOffset,
                                arraySize = itemSize,
                                indicatorColor = Color.White,
                                modifier = Modifier
                                    .height(bottomBarHeight),
                                indicatorShape = indicatorShape
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(bottomBarHeight)
                    .selectableGroup(),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = content
            )
        }
    }
}