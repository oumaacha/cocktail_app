package fr.enseirb.cocktail_app.View.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
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
import kotlinx.coroutines.delay

@Composable
fun ingredientScreen(ingredientService: IngredientService) : String {
    val ingredients = remember { mutableStateOf(emptyList<Ingredient>()) }
    var ingredient = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        val startTime = System.currentTimeMillis()
        val fetchedCategories = ingredientService.fetchIngredients()
        ingredients.value = fetchedCategories
        val elapsedTime = System.currentTimeMillis() - startTime
        if(elapsedTime < 2000) {
            delay(2000 - elapsedTime)
        }
        isLoading.value = false
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading.value) {
            CircularProgressIndicator()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    items = ingredients.value,
                    itemContent = {
                        OutlinedCardExample(it.name, { item -> ingredient.value = item })
                    }
                )
            }
        }
    }
    return ingredient.value
}