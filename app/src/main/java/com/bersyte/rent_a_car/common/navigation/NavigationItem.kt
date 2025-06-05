package com.bersyte.rent_a_car.common.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : NavigationItem("home", "Home", Icons.Default.Home)
    data object Rentals : NavigationItem("rentals", "Rentals", Icons.Default.CarRental)
    data object MyCars : NavigationItem("my-cars", "My Cars", Icons.Default.DirectionsCar)
    data object Profile : NavigationItem("profile", "Profile", Icons.Default.Person)
}
