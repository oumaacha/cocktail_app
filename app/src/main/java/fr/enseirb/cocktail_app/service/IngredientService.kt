package fr.enseirb.cocktail_app.service

import fr.enseirb.cocktail_app.model.Ingredient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class IngredientService {
    private val client = OkHttpClient()
    suspend fun fetchIngredients(): List<Ingredient> = withContext(Dispatchers.IO) {
        val url = URLS.INGREDIENTS
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
    private fun parseJson(json: String): List<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        try {
            val jsonObject = JSONObject(json)
            val ingredientsArray = jsonObject.getJSONArray("drinks")

            for (i in 0 until ingredientsArray.length()) {
                val ingredientObject = ingredientsArray.getJSONObject(i)
                val strIngredient1 = ingredientObject.getString("strIngredient1")
                val ingredient = Ingredient(strIngredient1,null)
                ingredients.add(ingredient)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ingredients
    }
}