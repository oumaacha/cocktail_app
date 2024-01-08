package fr.enseirb.cocktail_app.service
import fr.enseirb.cocktail_app.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class CategoryService {
    private val client = OkHttpClient()

    suspend fun fetchCategories(): List<Category> = withContext(Dispatchers.IO){
        val request = Request.Builder()
            .url(URLS.CATEGORIES)
            .build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return@withContext parseJson(response.body?.string() ?: "")
        }else{
            throw Exception("Failed to fetch data. HTTP status code: ${response.code}")
        }
    }
    private fun parseJson(json: String): List<Category> {
        val categories = mutableListOf<Category>()

        try {
            val jsonObject = JSONObject(json)
            val drinksArray = jsonObject.getJSONArray("drinks")

            for (i in 0 until drinksArray.length()) {
                val categoryObject = drinksArray.getJSONObject(i)
                val strCategory = categoryObject.getString("strCategory")
                val category = Category(strCategory)
                categories.add(category)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return categories
    }
}