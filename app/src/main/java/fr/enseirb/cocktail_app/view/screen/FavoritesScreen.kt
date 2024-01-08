package fr.enseirb.cocktail_app.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.enseirb.cocktail_app.service.DrinkService

@Composable
fun favoritesScreen(){
    var drinkService = DrinkService()
    var favorites = drinkService.getFavorites()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // List of favorites
        }
    }
}