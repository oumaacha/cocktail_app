package fr.enseirb.cocktail_app.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "recherche"){
        composable("recherche"){
            // recherche screen
        }
        composable("category"){
            // category screen
        }
        composable("ingredient"){
            // ingredient screen
        }
    }
}