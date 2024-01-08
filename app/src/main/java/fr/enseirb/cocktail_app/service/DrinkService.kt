package fr.enseirb.cocktail_app.service

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.enseirb.cocktail_app.model.Category
import fr.enseirb.cocktail_app.model.Drink
import fr.enseirb.cocktail_app.model.Ingredient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class DrinkService{
    private val client = OkHttpClient()
    private var favorites : ArrayList<Drink> = ArrayList()
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
    suspend fun drinkById(id:String): Drink? = withContext(Dispatchers.IO) {
        val url = URLS.DRINKS_DETAILS+id
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return@withContext parseJsonToDrink(response.body?.string() ?: "")
        }else{
            throw Exception("Failed to fetch data. HTTP status code: ${response.code}")
        }
    }
    suspend fun drinkByCategory(category:String): List<Drink> = withContext(Dispatchers.IO) {
        val url = URLS.DRINKS_CATEGORY+category
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return@withContext parseJson2(response.body?.string() ?: "")
        }else{
            throw Exception("Failed to fetch data. HTTP status code: ${response.code}")
        }
    }
    suspend fun drinkByIngredient(ingredient:String): List<Drink> = withContext(Dispatchers.IO) {
        val url = URLS.DRINKS_INGREDIENT+ingredient
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return@withContext parseJson2(response.body?.string() ?: "")
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
    private fun parseJsonToDrink(jsonString: String): Drink? {
        try {
            val jsonObject = JSONObject(jsonString)
            val drinksArray = jsonObject.getJSONArray("drinks")
            val ingredientList = mutableListOf<Ingredient>()

            if (drinksArray.length() > 0) {
                val firstDrinkObject = drinksArray.getJSONObject(0)
                val idDrink = firstDrinkObject.getString("idDrink")
                val strDrink = firstDrinkObject.getString("strDrink")
                val strAlcoholic = firstDrinkObject.getString("strAlcoholic")
                val strDrinkThumb = firstDrinkObject.getString("strDrinkThumb")
                val strGlass = firstDrinkObject.getString("strGlass")
                val strInstructions = firstDrinkObject.getString("strInstructions")
                val strCategory = firstDrinkObject.getString("strCategory")
                for (i in 1..15){
                    var ing = "strIngredient"
                    var mes = "strMeasure"
                    if(firstDrinkObject.getString(ing+i) != "null"){
                        val ingredient = Ingredient(firstDrinkObject.getString(ing+i),4)
                        ingredientList.add(ingredient)
                    }
                }

                return Drink(idDrink, strDrink, strAlcoholic, strDrinkThumb,Category(strCategory),strGlass,strInstructions,ingredientList)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
    private fun parseJson2(json: String): List<Drink> {
        val drinks = mutableListOf<Drink>()

        try {
            val jsonObject = JSONObject(json)
            val drinksArray = jsonObject.getJSONArray("drinks")

            for (i in 0 until drinksArray.length()) {
                val drinkObject = drinksArray.getJSONObject(i)
                val idDrink = drinkObject.getString("idDrink")
                val strDrink = drinkObject.getString("strDrink")
                val strDrinkThumb = drinkObject.getString("strDrinkThumb")
                val drink = Drink(idDrink,strDrink,null,strDrinkThumb,null,null,null,null)
                drinks.add(drink)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return drinks
    }
    public fun addToFavorite(drink:Drink, context: Context){
        val sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val KEY = drink.idDrink
        val json = Gson().toJson(drink)
        loadFavorites(context)
        favorites.add(drink)
        with (sharedPref.edit()) {
            putString("drinks", json)
            apply()
        }

    }
    public fun loadFavorites(context: Context){
        val sharedPref =context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val gson = Gson()
        var jsonData = sharedPref.getString("drinks",null)
        println(jsonData)
        if (favorites == null){
            favorites = ArrayList<Drink>()
        }
    }
    public fun  getFavorites() : ArrayList<Drink>{
        return favorites;
    }

}