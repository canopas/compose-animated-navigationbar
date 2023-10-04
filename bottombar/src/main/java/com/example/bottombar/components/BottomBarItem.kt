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
 *  @param [modifier] Modifier to be applied to the navigation bar.
 *  @param [selected] The index of the currently selected item.
 *  @param [onClick] The handler that will be invoked when user clicks on bottom bar item.
 *  @param [imageVector] The image vector that will be used to show item icon.
 *  @param [containerColor] The color to be applied on the container.
 *  @param [contentColor] The color to be applied on the content.
 *  @param [iconColor] The color to be applied on the icon.
 *  @param [textColor] The color to be applied on the text.
 *  @param [activeIndicatorColor] The color to be applied on the indicator when its selected/active. Applicable only for [ItemStyle.STYLE2]
 *  @param [inactiveIndicatorColor]  The color to be applied on the indicator when its inactive. Applicable only for [ItemStyle.STYLE2]
 *  @param [label] The text to be shown on bottom bar.
 *  @param [visibleItem] The item(either LABEL/ICON) that need to be shown when it is selected. Select from [VisibleItem]
 *  @param [itemStyle] The style to be applied on bottom bar items. Select from [ItemStyle]
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

        ItemStyle.STYLE4 -> {
            NavigationBarItem(
                modifier,
                selected,
                onClick,
                rememberVectorPainter(image = imageVector),
                containerColor,
                contentColor,
                iconColor
            )
        }

        ItemStyle.STYLE5 -> {
            // TODO
        }
    }
}
