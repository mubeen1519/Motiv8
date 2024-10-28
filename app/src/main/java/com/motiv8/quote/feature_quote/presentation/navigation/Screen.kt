package com.motiv8.quote.feature_quote.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val title: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector
) {
    object Home: Screen("Home", Icons.Filled.Home, Icons.Outlined.Home)
    object Favorite: Screen("Favorite", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder)
}
