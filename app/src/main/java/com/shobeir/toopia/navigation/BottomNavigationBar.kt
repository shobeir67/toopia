package com.shobeir.toopia.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shobeir.toopia.R
import com.shobeir.toopia.ui.theme.DeepBlue
import com.shobeir.toopia.ui.theme.font_standard
import com.shobeir.toopia.ui.theme.md_theme_light_onSurfaceVariant
import com.shobeir.toopia.ui.theme.md_theme_light_outline
import com.shobeir.toopia.ui.theme.md_theme_light_primary
import com.shobeir.toopia.ui.theme.md_theme_light_scrim
import com.shobeir.toopia.ui.theme.unSelectedBottomBar


@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
) {

    val items = listOf(
        BottomNavItem(
            name = stringResource(id = R.string.home),
            route = Screen.Home.route,
            selectedIcon = R.drawable.home,
            deSelectedIcon = R.drawable.home
        ),
        BottomNavItem(
            name = stringResource(id = R.string.gallery),
            route = Screen.Shop.route,
            selectedIcon = R.drawable.image_gallery,
            deSelectedIcon = R.drawable.image_gallery
        ),
        BottomNavItem(
            name = stringResource(id = R.string.karbordi),
            route = Screen.Karbordi.route,
            selectedIcon = R.drawable.app,
            deSelectedIcon = R.drawable.app
        ),


        BottomNavItem(
            name = stringResource(id = R.string.profile),
            route = Screen.Profile.route,
            selectedIcon = R.drawable.male_user,
            deSelectedIcon = R.drawable.male_user
        ),

        )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in items.map { it.route }

    if (showBottomBar) {
        BottomNavigation(
            modifier = Modifier.height(60.dp),
            backgroundColor = md_theme_light_outline,
            elevation = 5.dp
        ) {
            items.forEachIndexed { _, item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = md_theme_light_scrim,
                    unselectedContentColor = md_theme_light_onSurfaceVariant,
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (selected) {
                                    Icon(
                                        modifier = Modifier.height(24.dp),
                                        painter = painterResource(id = item.selectedIcon),
                                        contentDescription = item.name
                                    )

                            } else {
                                Icon(
                                        modifier = Modifier.height(24.dp),
                                        painter = painterResource(item.deSelectedIcon),
                                        contentDescription = item.name
                                    )
                            }
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                style = typography.bodySmall.copy(fontFamily = font_standard),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp),
                                color = if(selected) md_theme_light_scrim else md_theme_light_onSurfaceVariant
                            )
                        }
                    }
                )
            }
        }
    }

}