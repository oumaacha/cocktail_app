package fr.enseirb.cocktail_app.View.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.enseirb.cocktail_app.View.components.OutlinedCardExample
import fr.enseirb.cocktail_app.model.Ingredient
import fr.enseirb.cocktail_app.service.IngredientService

@Composable
fun ingredientScreen(ingredientService: IngredientService) {
    val ingredients = remember { mutableStateOf(emptyList<Ingredient>()) }
    LaunchedEffect(true) {
        val fetchedCategories = ingredientService.fetchIngredients()
        ingredients.value = fetchedCategories
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = ingredients.value,
                itemContent = {
                    OutlinedCardExample(it.name)
                }
            )
        }
    }
}