package com.example.bottombar.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bottombar.utils.VisibleItem

@Composable
fun RowScope.IconTextAnimator(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    @DrawableRes iconResource: Int,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    label: String,
    visibleItem: VisibleItem = VisibleItem.LABEL
) = IconTextAnimator(
    modifier,
    selected,
    onClick,
    painterResource(id = iconResource),
    containerColor,
    contentColor,
    iconColor,
    textColor,
    label,
    visibleItem
)

@Composable
fun RowScope.IconTextAnimator(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    imageVector: ImageVector,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    label: String,
    visibleItem: VisibleItem = VisibleItem.LABEL
) = IconTextAnimator(
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

@Composable
fun RowScope.IconTextAnimator(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    iconPainter: Painter,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    label: String,
    visibleItem: VisibleItem = VisibleItem.LABEL
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        modifier = Modifier
            .clickable(
                onClick = {
                    onClick()
                },
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = false, radius = 30.dp)
            )
            .weight(1f)
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = if (visibleItem == VisibleItem.LABEL) !selected else selected,
                enter = fadeIn() + slideInVertically(
                    tween(
                        easing = FastOutLinearInEasing
                    )
                ) { fullHeight -> -fullHeight },
                exit = slideOutVertically(
                    tween(
                        easing = FastOutLinearInEasing
                    )
                ) { fullHeight -> -fullHeight } + fadeOut()
            ) {
                Icon(
                    painter = iconPainter,
                    contentDescription = null,
                    tint = iconColor
                )
            }

            AnimatedVisibility(
                visible = if (visibleItem == VisibleItem.LABEL) selected else !selected,
                enter = fadeIn() + slideInVertically(
                    tween(
                        easing = FastOutLinearInEasing
                    )
                ) { fullHeight -> fullHeight },
                exit = slideOutVertically(
                    tween(
                        easing = FastOutLinearInEasing
                    )
                ) { fullHeight -> fullHeight } + fadeOut()
            ) {
                Text(
                    text = label,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}