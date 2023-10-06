package com.example.bottombar.model

/**
 *[STYLE1]: Any 1 item(either Icon or Label) will be visible at a time or both items can be visible
 *  at a time with slide-in & slide-out animation.
 *
 *[STYLE2]: Both items will be visible at a time with horizontal arrangement if it
 *  is currently selected with [IndicatorStyle.FILLED] as default indicator. (**NOTE:-** While using [STYLE2],
 *  make sure that indicatorStyle is [IndicatorStyle.NONE] in **AnimatedBottomBar**)
 *
 *[STYLE3]: Both items will be visible at a time with vertical arrangement and scaling animation if it
 *  is currently selected.
 *
 *[STYLE4]: Only Icon will be visible at a time with scale animation and
 * only selected icon will have icon-color and others as grayed out.
 *
 *[STYLE5]: Only Icon will be visible at a time with glowing background for selected item.
 *
 */
enum class ItemStyle {
    STYLE1,
    STYLE2,
    STYLE3,
    STYLE4,
    STYLE5
}