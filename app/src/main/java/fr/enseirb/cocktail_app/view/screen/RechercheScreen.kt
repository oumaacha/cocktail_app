package fr.enseirb.cocktail_app.view.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fr.enseirb.cocktail_app.model.Drink
import fr.enseirb.cocktail_app.service.DrinkService



@Composable
fun rechercheScreen(drinkService: DrinkService) : String{
    var id = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, bottom = 60.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val data = searchBar(drinkService)
            searchedList(data){
                clicked -> id.value = clicked
            }
        }
    }
    return id.value
}

@Composable
fun searchBar(drinkService: DrinkService) : List<Drink>{
    val drinkService = DrinkService()
    var data = remember { mutableStateOf(emptyList<Drink>()) }

    Row{
        var text by remember { mutableStateOf("")}
    val color = Color.Gray
    TextField(
        value = text,
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxWidth(0.9f),
        onValueChange = { text = it },
        label = { Text(text="Search") },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Icon")
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            backgroundColor = color.copy(
                TextFieldDefaults.BackgroundOpacity
            ),
            leadingIconColor = color.copy(
                TextFieldDefaults.IconOpacity
            ),
        )
    )
        LaunchedEffect(text){
            data.value = drinkService.fetchDrinks(text)
        }
}
    return data.value;
}

@Composable
fun searchedList(data:List<Drink>, onDrinkClicked: (String) -> Unit): String {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(data){
            item ->  cardDrink(item) {
                clickedDrinkId -> onDrinkClicked(item.idDrink)
        }
        }
        }
    return ""
    }

@Composable
fun cardDrink(drink:Drink, onDrinkClicked: (String) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable(onClick = {
                onDrinkClicked(drink.idDrink)
            })
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
