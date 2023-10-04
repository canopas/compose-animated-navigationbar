package com.example.bottombar.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bottombar.model.AnimationState
import com.example.bottombar.model.VisibleItem

/**
 * A composable function that creates view for **STYLE1** where selected item will
 * change on the basis of value provided in `visibleItem`
 */
@Composable
internal fun RowScope.NavigationBarItem(
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
    var animationState by remember { mutableStateOf(AnimationState.Start) }
    val scaleAnimation: Float by animateFloatAsState(
        if (animationState == AnimationState.Start) 1f else 0.9f,
        tween(easing = LinearEasing),
        label = ""
    )

    LaunchedEffect(key1 = selected, key2 = Unit, block = {
        animationState =
            if (selected && visibleItem == VisibleItem.BOTH) AnimationState.Finish else AnimationState.Start
    })

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
                .fillMaxHeight()
                .scale(scaleAnimation),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = (visibleItem == VisibleItem.BOTH && !selected),
                enter = fadeIn() + slideInVertically(
                    tween(easing = LinearEasing)
                ) { fullHeight -> -fullHeight },
                exit = slideOutVertically(
                    tween(easing = LinearEasing)
                ) { fullHeight -> -fullHeight } + fadeOut()
            ) {
                Icon(
                    painter = iconPainter,
                    contentDescription = null,
                    tint = iconColor
                )
            }

            AnimatedVisibility(
                visible = (visibleItem == VisibleItem.BOTH && selected),
                enter = fadeIn() + slideInVertically(
                    tween(easing = LinearEasing)
                ) { fullHeight -> fullHeight },
                exit = slideOutVertically(
                    tween(easing = LinearEasing)
                ) { fullHeight -> fullHeight } + fadeOut()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = iconPainter,
                        contentDescription = null,
                        tint = iconColor
                    )

                    Text(
                        text = label,
                        color = textColor,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        softWrap = false,
                        overflow = TextOverflow.Clip
                    )
                }
            }

            if (visibleItem == VisibleItem.BOTH) return@Surface

            AnimatedVisibility(
                visible = if (visibleItem == VisibleItem.LABEL) !selected else selected,
                enter = fadeIn() + slideInVertically(
                    tween(
                        easing = LinearEasing
                    )
                ) { fullHeight -> -fullHeight },
                exit = slideOutVertically(
                    tween(
                        easing = LinearEasing
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
                        easing = LinearEasing
                    )
                ) { fullHeight -> fullHeight },
                exit = slideOutVertically(
                    tween(
                        easing = LinearEasing
                    )
                ) { fullHeight -> fullHeight } + fadeOut()
            ) {
                Text(
                    text = label,
                    color = textColor,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}

/**
 * A composable function that creates view for **STYLE2** where selected item will
 * have **FILLED** indicators and horizontal item placement
 */
@Composable
internal fun NavigationBarItem(
    modifier: Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    iconPainter: Painter,
    contentColor: Color,
    iconColor: Color,
    textColor: Color,
    label: String,
    activeIndicatorColor: Color,
    inactiveIndicatorColor: Color
) {
    val color by animateColorAsState(
        targetValue = if (selected) activeIndicatorColor else inactiveIndicatorColor,
        animationSpec = tween(easing = LinearEasing),
        label = ""
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxHeight()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = false, radius = 30.dp)
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = color,
            contentColor = contentColor,
            shape = RoundedCornerShape(maxHeight / 2)
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        vertical = maxHeight / 12,
                        horizontal = maxHeight / 4
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.height(this@BoxWithConstraints.maxHeight / 2),
                    painter = iconPainter,
                    contentDescription = null,
                    tint = iconColor
                )
                AnimatedVisibility(
                    visible = selected
                ) {
                    Text(
                        text = label,
                        color = textColor,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        softWrap = false,
                        overflow = TextOverflow.Clip,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}

/**
 * A composable function that creates view for **STYLE3** where selected item will
 * have `scale animation` and vertical item placement
 */
@Composable
internal fun RowScope.NavigationBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    iconPainter: Painter,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    label: String
) {
    var animationState by remember { mutableStateOf(AnimationState.Start) }
    val scaleAnimation: Float by animateFloatAsState(
        if (animationState == AnimationState.Start) 1f else 0.8f,
        tween(easing = LinearEasing),
        label = ""
    )

    LaunchedEffect(key1 = selected, key2 = Unit, block = {
        animationState = if (selected) AnimationState.Finish else AnimationState.Start
    })

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
                .scale(scaleAnimation)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = iconPainter,
                contentDescription = null,
                tint = iconColor
            )

            AnimatedVisibility(selected, enter = fadeIn(tween(delayMillis = 100))) {
                Text(
                    text = label,
                    color = textColor,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}