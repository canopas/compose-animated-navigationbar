# AnimatedBottomBarCompose

**AnimatedBottomBarCompose** is a Jetpack Compose library that simplifies the creation of stylish
Bottom Navigation Bars with
customizable animations. It allows you to easily integrate attractive navigation bars into your
Android app, enhancing the user experience.
<table>
  <tr>
    <td align="center">
       <b>LINE INDICATOR</b>
       <br />
       <br />
      <img src="https://github.com/canopas/AnimatedBottomBarCompose/assets/98312779/ed9d5ac1-dc6f-4b91-8c98-e6c8826f4736"  width="80%" height="80%">
    </td>
    <td align="center">
       <b>FILLED INDICATOR</b>
       <br />
       <br />
      <img src="https://github.com/canopas/AnimatedBottomBarCompose/assets/98312779/75622692-fded-4891-9d32-f2540e8e4744"  width="80%" height="80%">
    </td>

  </tr>

  <tr>
    <td align="center">
       <b>DOT INDICATOR</b>
       <br />
       <br />
     <img src="https://github.com/canopas/AnimatedBottomBarCompose/assets/98312779/b0dc3420-9c79-4f05-892f-1c67ebea5817"  width="80%" height="80%">
    </td>
    <td align="center">
       <b>WORM INDICATOR</b>
       <br />
       <br />
      <img src="https://github.com/canopas/AnimatedBottomBarCompose/assets/98312779/8b16d97a-a1c8-40ed-a6be-5fb05fbcd909"  width="80%" height="80%">
    </td>
</tr>
</table>


## Features

- **Multiple Styles:** Choose from a variety of pre-defined styles for your Bottom Navigation Bar or
  create your custom style.
- **Animation Options:** Choose from variety of eye-catching animations for your navigation bar
  elements, making your app more engaging.
- **Customization:** Customize colors, icons, and animations to match your app's branding and
  design.



## Configuration

Available on [Maven Central](https://central.sonatype.com/artifact/com.canopas.compose-animated-navigationbar/bottombar).

Add the dependency
```gradle
    implementation 'com.canopascompose-animated-navigationbar:bottombar:1.0.0'

```


## Sample Usage

Integrating **AnimatedBottomBarCompose** into your Android app is a breeze! Follow these simple
steps to get started:

1. First, set up your navigation controller:

```
val navController = rememberNavController()
val navBackStackEntry by navController.currentBackStackEntryAsState()
val currentRoute = navBackStackEntry?.destination?.route
val navigationItems = MainNavigation::class.nestedClasses.map {
    it.objectInstance as MainNavigation
}
var selectedItem by remember { mutableIntStateOf(0) }
```

2. Next, add **AnimatedBottomBarCompose** to your app's Scaffold as the bottom bar:

```
Scaffold(
    bottomBar = {
        AnimatedBottomBar(
            selectedItem = selectedItem,
            itemSize = navigationItems.take(3).size,
            containerColor = Color.LightGray,
            indicatorStyle = IndicatorStyle.LINE
        ) {
            navigationItems.forEachIndexed { index, navigationItem ->
                BottomBarItem(
                    selected = currentRoute == navigationItem.route,
                    onClick = {
                        if (currentRoute != navigationItem.route) {
                            selectedItem = index
                            // ... Navigation Stuff
                    },
                    imageVector = navigationItem.icon,
                    label = navigationItem.title,
                    containerColor = Color.Transparent
                )
            }
        }
    }
) {
// ... (rest of your app content)
}
```

## Demo

To see **AnimatedBottomBarCompose** in action, check out
our [Sample](https://github.com/canopas/AnimatedBottomBarCompose/tree/master/app) app where you can
explore various styles and animation options.

## Bugs and Feedback

For bugs, questions and discussions please use
the [Github Issues](https://github.com/canopas/AnimatedBottomBarCompose/issues)

## Credits

**AnimatedBottomBarCompose** is owned and maintained by the [Canopas team](https://canopas.com/).
For project updates and releases, you can follow them on Twitter
at [@canopassoftware](https://twitter.com/canopassoftware).

# Licence

```
Copyright 2023 Canopas Software LLP

Licensed under the Apache License, Version 2.0 (the "License");
You won't be using this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
