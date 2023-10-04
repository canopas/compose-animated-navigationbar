package com.example.animatedbottombarcompose.composables

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
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombar.model.ItemStyle
import com.example.bottombar.model.VisibleItem

@Composable
fun Screen2(navigationItems: List<MainNavigation>) {
    var selectedItem by remember { mutableIntStateOf(0) }
    var itemStyle: ItemStyle by remember {
        mutableStateOf(ItemStyle.STYLE3)
    }
    val itemStyles = listOf(ItemStyle.STYLE1, ItemStyle.STYLE2, ItemStyle.STYLE3, ItemStyle.STYLE4)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Spacer(modifier = Modifier.height(50.dp))

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

        Spacer(modifier = Modifier.height(100.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = if (itemStyle != ItemStyle.STYLE2) IndicatorStyle.DOT else IndicatorStyle.NONE,
            containerColor = Color.Transparent,
            indicatorColor = Color.Red
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
                    iconColor = if (selected) Color.Red else Color.Black,
                    textColor = if (selected) Color.Red else Color.Black,
                    visibleItem = VisibleItem.BOTH,
                    itemStyle = itemStyle
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = if (itemStyle != ItemStyle.STYLE2) IndicatorStyle.DOT else IndicatorStyle.NONE,
            indicatorColor = Color.White
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
                    visibleItem = VisibleItem.BOTH,
                    itemStyle = itemStyle
                )
            }
        }

        Spacer(modifier = Modifier.height(150.dp))
    }
}
