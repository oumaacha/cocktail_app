package fr.enseirb.cocktail_app.service

import fr.enseirb.cocktail_app.model.Category
import fr.enseirb.cocktail_app.model.Drink
import fr.enseirb.cocktail_app.model.Ingredient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class DrinkService {
    private val client = OkHttpClient()

    suspend fun fetchDrinks(drink: String): List<Drink> = withContext(Dispatchers.IO){
        val url = URLS.DRINKS+drink
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return@withContext parseJson(response.body?.string() ?: "")
        }else{
            throw Exception("Failed to fetch data. HTTP status code: ${response.code}")
        }
    }
    private fun parseJson(json: String): List<Drink> {
        val drinks = mutableListOf<Drink>()
        val ingredientList = mutableListOf<Ingredient>()

        try {
            val jsonObject = JSONObject(json)
            val drinksArray = jsonObject.getJSONArray("drinks")

            for (i in 0 until drinksArray.length()) {
                val drinkObject = drinksArray.getJSONObject(i)
                val idDrink = drinkObject.getString("idDrink")
                val strDrink = drinkObject.getString("strDrink")
                val strAlcoholic = drinkObject.getString("strAlcoholic")
                val strDrinkThumb = drinkObject.getString("strDrinkThumb")
                val strGlass = drinkObject.getString("strGlass")
                val strInstructions = drinkObject.getString("strInstructions")
                val strCategory = drinkObject.getString("strCategory")
                for (i in 1..15){
                    var ing = "strIngredient"
                    if(drinkObject.getString(ing+i) !=null){
                        val ingredient = Ingredient(drinkObject.getString(ing+i),4)
                        ingredientList.add(ingredient)
                    }
                }
                val drink = Drink(idDrink,strDrink,strAlcoholic,strDrinkThumb,Category(strCategory),strGlass,strInstructions,ingredientList)
                drinks.add(drink)
                ingredientList.clear()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return drinks
    }
}