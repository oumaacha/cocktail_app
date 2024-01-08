package fr.enseirb.cocktail_app.View.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fr.enseirb.cocktail_app.model.Drink

@Composable
fun listByCategory(data:List<Drink>) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, bottom = 60.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(data){
                item ->  cardDrinkByCategory(item)
        }
    }

}
@Composable
fun cardDrinkByCategory(drink:Drink){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable(onClick = {})
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.White,
            elevation = 5.dp
        ) {
            Column {
                AsyncImage(model = drink.strDrinkThumb, contentDescription = "Image")
                Text(text = drink.strDrink, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp))
            }
        }
    }
}