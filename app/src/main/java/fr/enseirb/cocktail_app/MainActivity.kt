package fr.enseirb.cocktail_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import fr.enseirb.cocktail_app.ui.theme.Cocktail_appTheme
import fr.enseirb.cocktail_app.view.screen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Cocktail_appTheme {
                val navController = rememberNavController()
                MainScreen(navController = navController,this)
            }
        }
    }
}