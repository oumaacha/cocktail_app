package fr.enseirb.cocktail_app.model

data class Drink(
   val idDrink: String,
   val strDrink: String,
   val strAlcoholic: String,
   val strDrinkThumb: String,

   val category: Category?,
   val strGlass: String?,
   val strInstructions: String?,
   val ingredient: List<Ingredient>?

) {}

