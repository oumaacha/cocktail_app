package fr.enseirb.cocktail_app.view.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.enseirb.cocktail_app.R
@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = colorResource(R.color.primarygreen),
        elevation = 5.dp
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = item.route == navController.currentDestination?.route,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(30.dp),
                        tint = Color.Unspecified
                    )
                }
            )
        }
    }
}