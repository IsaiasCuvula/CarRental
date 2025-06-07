package com.bersyte.rent_a_car.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bersyte.rent_a_car.features.admin.home.ui.screens.AdminDashboardScreen
import com.bersyte.rent_a_car.features.admin.home.ui.screens.OperatorDashboardScreen
import com.bersyte.rent_a_car.features.auth.ui.screens.AuthScreen
import java.util.Locale

@Composable
fun MainAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            AuthScreen(
                onLoginSuccess = { role ->
                    val route = when (role.name.toLowerCase(Locale.ROOT)) {
                        "customer" -> "customer_dashboard"
                        "operator" -> "operator_dashboard"
                        "admin" -> "admin_dashboard"
                        else -> "login"
                    }

                    navController.navigate(route) {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("customer_dashboard") {
            MainNavigationScreen()
        }

        composable("operator_dashboard") {
            OperatorDashboardScreen()
        }

        composable("admin_dashboard") {
            AdminDashboardScreen()
        }
    }
}
