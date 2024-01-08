package fr.enseirb.cocktail_app.view.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import fr.enseirb.cocktail_app.View.screen.drinkDetails
import fr.enseirb.cocktail_app.model.Drink
import fr.enseirb.cocktail_app.service.DrinkService
import fr.enseirb.cocktail_app.view.screen.rechercheScreen
import fr.enseirb.cocktail_app.View.screen.categoryScreen
import fr.enseirb.cocktail_app.View.screen.ingredientScreen
import fr.enseirb.cocktail_app.View.screen.listByCategory
import fr.enseirb.cocktail_app.service.CategoryService
import fr.enseirb.cocktail_app.service.IngredientService
import fr.enseirb.cocktail_app.view.screen.favoritesScreen
import fr.enseirb.cocktail_app.view.screen.listByIngredient


@Composable
fun Navigation(navController: NavHostController,context: Context) {
    var loading = remember { mutableStateOf(false) }
    val drinkService = DrinkService()
    val categoryService = CategoryService()
    val ingredientService = IngredientService()
    var ingredient = remember { mutableStateOf("") }
    var drink = remember { mutableStateOf("") }
    var category = remember { mutableStateOf("") }
    NavHost(navController = navController, startDestination = "recherche"){
        var datadrink: Drink? = null
        var drinksByCategory : List<Drink>? = null
        var drinksByIngredient : List<Drink>? = null
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
                    drinkDetails(drink = datadrink!!,context)
                }
            }
        }
        composable("category"){
            drink.value = ""
            ingredient.value = ""
            LaunchedEffect(Unit) {
                category.value = ""
            }
            if(category.value.equals("")){
                category.value = categoryScreen(categoryService)
            } else{
                LaunchedEffect(category.value) {
                    loading.value = true
                    val result = drinkService.drinkByCategory(category.value)
                    drinksByCategory = result
                    loading.value = false
                }

                if (drinksByCategory != null && !loading.value) {
                    listByCategory(data = drinksByCategory!!)
                }
            }
        }
        composable("ingredient"){
            drink.value = ""
            category.value = ""
            LaunchedEffect(Unit) {
                ingredient.value = ""
            }
            if(ingredient.value.equals("")){
                ingredient.value = ingredientScreen(ingredientService)
            } else{
                LaunchedEffect(ingredient.value) {
                    loading.value = true
                    val result = drinkService.drinkByIngredient(ingredient.value)
                    drinksByIngredient = result
                    loading.value = false
                }

                if (drinksByIngredient != null && !loading.value) {
                    listByIngredient(data = drinksByIngredient!!)
                }
            }
        }
        composable("favorites"){
            drink.value = ""
            category.value = ""
            ingredient.value = ""
            val favorites = drinkService.getFavorites()
            favoritesScreen(favorites)
        }
    }
}