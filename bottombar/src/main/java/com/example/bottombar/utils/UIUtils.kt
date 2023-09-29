package com.example.bottombar.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.conditional(
    condition: Boolean,
    orElse: (@Composable Modifier.() -> Modifier)? = null,
    modifier: @Composable Modifier.() -> Modifier
): Modifier = composed {
    if (condition) {
        modifier.invoke(this)
    } else {
        orElse?.invoke(this) ?: this
    }
}