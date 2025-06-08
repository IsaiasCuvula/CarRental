package com.bersyte.rent_a_car.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bersyte.rent_a_car.features.company.admin.ui.screens.AdminDashboardManagement
import com.bersyte.rent_a_car.features.auth.ui.screens.AuthScreen
import com.bersyte.rent_a_car.features.auth.viewmodels.AuthViewModel
import com.bersyte.rent_a_car.features.company.operator.ui.screens.OperatorDashboardApp

@Composable
fun MainAppNavigation(
   authViewModel: AuthViewModel = viewModel()
) {
    val navController = rememberNavController()
    val authResponse by authViewModel.authResponse.collectAsState()

    val startDestination = remember(authResponse) {
        val data = authResponse?.data
        when (data?.role?.lowercase()) {
            null, "" -> "login"
            "customer" -> "customer_dashboard"
            "operator" -> "operator_dashboard"
            "admin" -> "admin_dashboard"
            else -> "login"
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
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
            CustomerNavigationScreen(navController = navController)
        }
        composable("operator_dashboard") {
            OperatorDashboardApp()
        }
        composable("admin_dashboard") {
            AdminDashboardManagement()
        }
    }
}
