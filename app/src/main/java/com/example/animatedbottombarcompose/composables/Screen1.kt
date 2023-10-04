package com.example.animatedbottombarcompose.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.animatedbottombarcompose.model.MainNavigation
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorDirection
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombar.model.ItemStyle
import com.example.bottombar.model.VisibleItem

@Composable
fun Screen1(navigationItems: List<MainNavigation>) {

    var selectedItem by remember { mutableIntStateOf(0) }
    var indicatorDirection: IndicatorDirection by remember {
        mutableStateOf(IndicatorDirection.TOP)
    }
    var visibleItem: VisibleItem by remember {
        mutableStateOf(VisibleItem.ICON)
    }
    var itemStyle: ItemStyle by remember {
        mutableStateOf(ItemStyle.STYLE3)
    }
    var indicatorStyle: IndicatorStyle by remember {
        mutableStateOf(IndicatorStyle.LINE)
    }
    val directionOptions = listOf(IndicatorDirection.TOP, IndicatorDirection.BOTTOM)
    val visibilityOptions = listOf(VisibleItem.ICON, VisibleItem.LABEL, VisibleItem.BOTH)
    val itemStyles = listOf(ItemStyle.STYLE1, ItemStyle.STYLE2, ItemStyle.STYLE3, ItemStyle.STYLE4)
    val indicatorStyles =
        listOf(IndicatorStyle.NONE, IndicatorStyle.LINE, IndicatorStyle.DOT, IndicatorStyle.FILLED)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Indicator Direction:",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            directionOptions.forEach {
                RadioButton(selected = it == indicatorDirection, onClick = {
                    indicatorDirection = it
                })
                Text(text = it.name)
            }
        }

        Text(
            text = "Visible Item:",
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            visibilityOptions.forEach {
                RadioButton(selected = it == visibleItem, onClick = {
                    visibleItem = it
                })
                Text(text = it.name)
            }
        }

        Text(
            text = "Item Style:",
            style = MaterialTheme.typography.titleMedium
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                itemStyles.take(2).forEach {
                    RadioButton(selected = it == itemStyle, onClick = {
                        itemStyle = it
                    })
                    Text(text = it.name)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                itemStyles.takeLast(2).forEach {
                    RadioButton(selected = it == itemStyle, onClick = {
                        itemStyle = it
                    })
                    Text(text = it.name)
                }
            }
        }

        Text(
            text = "Indicator Style:",
            style = MaterialTheme.typography.titleMedium
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                indicatorStyles.take(2).forEach {
                    RadioButton(selected = it == indicatorStyle, onClick = {
                        indicatorStyle = it
                    })
                    Text(text = it.name)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                indicatorStyles.takeLast(2).forEach {
                    RadioButton(selected = it == indicatorStyle, onClick = {
                        indicatorStyle = it
                    })
                    Text(text = it.name)
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = if (itemStyle != ItemStyle.STYLE2) indicatorStyle else IndicatorStyle.NONE,
            indicatorDirection = indicatorDirection,
            containerColor = Color.Transparent,
            indicatorColor = Color.LightGray
        ) {
            navigationItems.forEachIndexed { index, navigationItem ->
                val selected = index == selectedItem
                BottomBarItem(
                    selected = selected,
                    onClick = {
                        selectedItem = index
                    },
                    imageVector = navigationItem.icon,
                    label = navigationItem.title,
                    iconColor = if (selected) Color.Black else Color.Gray,
                    textColor = if (selected) Color.Black else Color.Gray,
                    visibleItem = visibleItem,
                    itemStyle = itemStyle
                )
            }
        }


        Spacer(modifier = Modifier.height(50.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = if (itemStyle != ItemStyle.STYLE2) indicatorStyle else IndicatorStyle.NONE,
            indicatorDirection = indicatorDirection
        ) {
            navigationItems.forEachIndexed { index, navigationItem ->
                val selected = index == selectedItem
                BottomBarItem(
                    selected = selected,
                    onClick = {
                        selectedItem = index
                    },
                    imageVector = navigationItem.icon,
                    label = navigationItem.title,
                    iconColor = if (selected) Color.White else Color.Black,
                    textColor = if (selected) Color.White else Color.Black,
                    visibleItem = visibleItem,
                    itemStyle = itemStyle
                )
            }
        }

        Spacer(modifier = Modifier.height(150.dp))
    }
}
