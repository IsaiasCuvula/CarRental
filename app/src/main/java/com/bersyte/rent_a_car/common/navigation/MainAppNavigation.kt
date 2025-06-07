package com.bersyte.rent_a_car.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bersyte.rent_a_car.features.company.admin.ui.screens.AdminDashboardManagement
import com.bersyte.rent_a_car.features.auth.ui.screens.AuthScreen
import com.bersyte.rent_a_car.features.company.operator.ui.screens.OperatorDashboardApp

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
                    val route = when (role.name.lowercase()) {
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
            OperatorDashboardApp()
        }

        composable("admin_dashboard") {
            AdminDashboardManagement()
        }
    }
}
