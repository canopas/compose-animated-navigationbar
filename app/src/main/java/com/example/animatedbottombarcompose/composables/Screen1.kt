package com.example.animatedbottombarcompose.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            text = "**Checkout INDICATORS with different STYLES(animations)**",
            style = MaterialTheme.typography.titleMedium,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "LINE INDICATOR",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = IndicatorStyle.LINE,
            containerShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
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
                    visibleItem = VisibleItem.ICON,
                    itemStyle = ItemStyle.STYLE1,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = IndicatorStyle.LINE,
            containerShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
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
                    visibleItem = VisibleItem.LABEL,
                    itemStyle = ItemStyle.STYLE1,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = IndicatorStyle.LINE,
            containerShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
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
                    visibleItem = VisibleItem.BOTH,
                    itemStyle = ItemStyle.STYLE1,
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = IndicatorStyle.LINE,
            containerShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            indicatorDirection = IndicatorDirection.BOTTOM,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
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
                    visibleItem = VisibleItem.ICON,
                    itemStyle = ItemStyle.STYLE3,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = IndicatorStyle.LINE,
            containerShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            indicatorDirection = IndicatorDirection.BOTTOM,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
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
                    visibleItem = VisibleItem.LABEL,
                    itemStyle = ItemStyle.STYLE4,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            indicatorStyle = IndicatorStyle.LINE,
            containerShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            indicatorDirection = IndicatorDirection.BOTTOM,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
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
                    visibleItem = VisibleItem.BOTH,
                    itemStyle = ItemStyle.STYLE5,
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "DOT INDICATOR",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.errorContainer,
            indicatorStyle = IndicatorStyle.DOT,
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
                    itemStyle = ItemStyle.STYLE1,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.errorContainer,
            indicatorStyle = IndicatorStyle.DOT,
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
                    iconColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    textColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    itemStyle = ItemStyle.STYLE3,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.errorContainer,
            indicatorStyle = IndicatorStyle.DOT,
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
                    iconColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    textColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    itemStyle = ItemStyle.STYLE4,
                    activeIndicatorColor = Color.White,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.errorContainer,
            indicatorStyle = IndicatorStyle.DOT,
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
                    iconColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    textColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    itemStyle = ItemStyle.STYLE5,
                    activeIndicatorColor = Color.White,
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "WORM INDICATOR",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            indicatorStyle = IndicatorStyle.WORM,
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
                    iconColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    textColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    itemStyle = ItemStyle.STYLE1,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            indicatorStyle = IndicatorStyle.WORM,
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
                    iconColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    textColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    itemStyle = ItemStyle.STYLE3,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            indicatorStyle = IndicatorStyle.WORM,
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
                    iconColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    textColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    itemStyle = ItemStyle.STYLE4,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            indicatorStyle = IndicatorStyle.WORM,
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
                    iconColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    textColor = if (selected) Color.Black else Color.Black.copy(0.8f),
                    itemStyle = ItemStyle.STYLE5,
                    activeIndicatorColor = Color.White,
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "FILLED INDICATOR",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            indicatorStyle = IndicatorStyle.FILLED,
            indicatorColor = MaterialTheme.colorScheme.background,
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
                    itemStyle = ItemStyle.STYLE1,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            indicatorStyle = IndicatorStyle.FILLED,
            indicatorColor = MaterialTheme.colorScheme.background,
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
                    itemStyle = ItemStyle.STYLE3,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            indicatorStyle = IndicatorStyle.FILLED,
            indicatorColor = MaterialTheme.colorScheme.background,
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
                    itemStyle = ItemStyle.STYLE4,
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            indicatorStyle = IndicatorStyle.FILLED,
            indicatorColor = MaterialTheme.colorScheme.background,
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
                    itemStyle = ItemStyle.STYLE5,
                    iconColor = if (selected) Color.White else Color.Black,
                    glowingBackground =
                        Brush.radialGradient(
                            listOf(
                                Color.Black,
                                Color.Transparent,
                                Color.Transparent,
                            ),
                        ),
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.size,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
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
                    itemStyle = ItemStyle.STYLE2,
                    iconColor = if (selected) Color.Black else Color.White,
                    textColor = if (selected) Color.Black else Color.White,
                    activeIndicatorColor = Color.White,
                )
            }
        }
        Spacer(modifier = Modifier.height(150.dp))
    }
}
