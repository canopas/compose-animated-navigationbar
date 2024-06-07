package com.example.bottombar.model

/**
 * Indicates which thing will be visible when an item is selected.
 *
 * **[VisibleItem] will be applicable only for [ItemStyle.STYLE1]**
 *
 * [ICON]: Icon will be visible for selected item and Label for others.
 *
 * [LABEL]: Label will be visible for selected item and Icon for others.
 *
 * [BOTH]: Both Icon & Label will be visible for selected Item & only Icon for others.
 */
enum class VisibleItem {
    ICON,
    LABEL,
    BOTH,
}
