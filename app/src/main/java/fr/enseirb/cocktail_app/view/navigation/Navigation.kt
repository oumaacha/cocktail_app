package fr.enseirb.cocktail_app.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import fr.enseirb.cocktail_app.model.Drink
import fr.enseirb.cocktail_app.service.DrinkService
import fr.enseirb.cocktail_app.view.screen.rechercheScreen


@Composable
fun Navigation(navController: NavHostController) {
    var loading = remember { mutableStateOf(false) }
    val drinkService = DrinkService()
    var drink = remember { mutableStateOf("") }
    NavHost(navController = navController, startDestination = "recherche"){
        var datadrink: Drink? = null
        composable("recherche"){
            LaunchedEffect(Unit) {
                drink.value = ""
            }
            if(drink.value.equals("")){
                drink.value = rechercheScreen(drinkService)
            } else{
                LaunchedEffect(drink.value) {
                    loading.value = true
                    val result = drinkService.drinkById(drink.value)
                    datadrink = result
                    loading.value = false
                }
                if (datadrink != null && !loading.value) {
                    drinkDetails(drink = datadrink!!)
                }
            }
        }
        composable("category"){
            // category screen
        }
        composable("ingredient"){
            // ingredient screen
        }
    }
}