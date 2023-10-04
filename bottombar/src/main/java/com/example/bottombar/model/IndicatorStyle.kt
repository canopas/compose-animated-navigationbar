package com.example.bottombar.model

/**
 * Indicates which type of indicator should be shown.
 *
 * [NONE]: No indicator will be shown.
 *
 * [DOT]: Dot indicator will be shown(only at bottom direction).
 *
 * [LINE]: Line indicator(Horizontal Tab) will be shown at specified direction.
 *
 * [FILLED]: Rounded indicator(Filled) will be shown at center of bottom bar.
 */
enum class IndicatorStyle {
    NONE,
    DOT,
    LINE,
    FILLED
}