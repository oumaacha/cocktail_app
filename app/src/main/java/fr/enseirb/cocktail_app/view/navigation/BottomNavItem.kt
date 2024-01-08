package fr.enseirb.cocktail_app.view.navigation

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: Painter
)