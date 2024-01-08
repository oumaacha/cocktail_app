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
import fr.enseirb.cocktail_app.model.Category
import fr.enseirb.cocktail_app.service.CategoryService
import fr.enseirb.cocktail_app.View.components.OutlinedCardExample

@Composable
fun categoryScreen(categoryService: CategoryService) : String{
    val categories = remember { mutableStateOf(emptyList<Category>()) }
    var category = remember { mutableStateOf("") }
    LaunchedEffect(true) {
        val fetchedCategories = categoryService.fetchCategories()
        categories.value = fetchedCategories
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = categories.value,
                itemContent = {
                    OutlinedCardExample(it.name) { item ->
                        category.value = item
                    }
                }
            )
        }
    }
    return category.value
}