package com.example.bottombar.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.example.bottombar.model.ItemStyle
import com.example.bottombar.model.VisibleItem

/**
 * A composable function that creates view for navigation items according to provided item style.
 *  @param [modifier] Modifier to be applied to the navigation bar
 *  @param [selected] The index of the currently selected item
 *  @param [onClick] The color of the navigation bar
 *  @param [imageVector] The color of the moving ball
 *  @param [containerColor] The corner radius of the navigation bar
 *  @param [contentColor] The animation to be applied to the moving ball
 *  @param [iconColor] The animation to be applied to the navigation bar to indent selected item
 *  @param [textColor] The composable content of the navigation bar
 *  @param [activeIndicatorColor] The composable content of the navigation bar
 *  @param [inactiveIndicatorColor] The composable content of the navigation bar
 *  @param [label] The direction where indicator need to be placed(Top/Bottom)
 *  @param [visibleItem] The shape to be applied on the indicator
 *  @param [itemStyle] The composable content of the navigation bar
 */
@Composable
fun RowScope.BottomBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    imageVector: ImageVector,
    containerColor: Color = Color.Transparent,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    activeIndicatorColor: Color = MaterialTheme.colorScheme.onPrimaryContainer.copy(0.2f),
    inactiveIndicatorColor: Color = Color.Transparent,
    label: String,
    visibleItem: VisibleItem = VisibleItem.ICON,
    itemStyle: ItemStyle = ItemStyle.STYLE1
) {
    when (itemStyle) {
        ItemStyle.STYLE1 -> {
            NavigationBarItem(
                modifier,
                selected,
                onClick,
                rememberVectorPainter(image = imageVector),
                containerColor,
                contentColor,
                iconColor,
                textColor,
                label,
                visibleItem
            )
        }

        ItemStyle.STYLE2 -> {
            NavigationBarItem(
                modifier,
                selected,
                onClick,
                rememberVectorPainter(image = imageVector),
                contentColor,
                iconColor,
                textColor,
                label,
                activeIndicatorColor,
                inactiveIndicatorColor
            )
        }

        ItemStyle.STYLE3 -> {
            NavigationBarItem(
                modifier,
                selected,
                onClick,
                rememberVectorPainter(image = imageVector),
                containerColor,
                contentColor,
                iconColor,
                textColor,
                label
            )
        }
    }
}
