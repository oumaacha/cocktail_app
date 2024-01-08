package fr.enseirb.cocktail_app.view.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.enseirb.cocktail_app.R
import fr.enseirb.cocktail_app.view.navigation.BottomNavItem
import fr.enseirb.cocktail_app.view.navigation.BottomNavBar
import fr.enseirb.cocktail_app.view.navigation.Navigation

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun MainScreen(navController: NavHostController) {
    val (currentScreen, setCurrentScreen) = remember { mutableStateOf("Home") } /////////
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = currentScreen)
                    }
                },
                modifier = Modifier.height(60.dp),
            )
        },
        content = {
            Navigation(navController = navController)
        },
        bottomBar = {
            BottomNavBar(
                modifier = Modifier.height(60.dp),
                items = listOf(
                    BottomNavItem(
                        name = "Recherche",
                        route = "recherche",
                        icon = painterResource(R.drawable.search_icon)
                    ),
                    BottomNavItem(
                        name = "Category",
                        route = "category",
                        icon = painterResource(R.drawable.category_icon)
                    ),
                    BottomNavItem(
                        name = "Ingredient",
                        route = "ingredient",
                        icon = painterResource(R.drawable.ingredient_icon)
                    ),
                    BottomNavItem(
                        name = "Favorites",
                        route = "favorites",
                        icon = painterResource(R.drawable.category_icon)
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    )
}