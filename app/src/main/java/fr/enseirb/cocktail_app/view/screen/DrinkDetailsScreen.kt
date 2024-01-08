package fr.enseirb.cocktail_app.View.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fr.enseirb.cocktail_app.model.Drink

@Composable
fun drinkDetails(drink: Drink){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, bottom = 60.dp, start = 10.dp, end = 10.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(20.dp)
            ){
                Text(text = drink.strDrink, color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
            AsyncImage(model = drink.strDrinkThumb, contentDescription = "Image", modifier = Modifier.fillMaxWidth())
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(20.dp)
            ){
                Text(text = "Category : " + drink.category?.name, color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 2.dp, Color.Gray)
                    .padding(20.dp)
            ){
                Text(text = "Served in : " + drink.strGlass, color = Color.Black, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(20.dp)
            ){
                Text(text = "Instructions : ", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 2.dp, Color.Gray)
                    .padding(20.dp)
            ){
                drink.strInstructions?.let { Text(text = it) }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(20.dp)
            ){
                Text(text = "Ingredients : ", color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 2.dp, Color.Gray)
                    .padding(20.dp)
            ){
                drink.ingredient?.forEach { ing ->
                    Row{
                        Text(text = ing.name)
                        Spacer(Modifier.width(10.dp))
                        Text(text = ing.measure.toString())
                    }

                }
            }
        }
    }
}