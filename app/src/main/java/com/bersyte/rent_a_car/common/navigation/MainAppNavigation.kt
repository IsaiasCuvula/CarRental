package com.bersyte.rent_a_car.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bersyte.rent_a_car.features.auth.ui.screens.AuthScreen
import com.bersyte.rent_a_car.features.auth.viewmodels.AuthViewModel

@Composable
fun MainAppNavigation(
   authViewModel: AuthViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val authResponse by authViewModel.authResponse.collectAsState()

    LaunchedEffect ("GetCustomerToken"){
        authViewModel.tokenManager.getAuthResponse()
    }

    NavHost(
        navController = navController,
        startDestination = "start"
    ) {

        composable("start") {
            val data = authResponse?.data
            val target = when (data?.role?.lowercase()) {
                "customer" -> "customer_dashboard"
                "operator" -> "operator_dashboard"
                "admin" -> "admin_dashboard"
                else -> "login"
            }

            // Immediate redirection based on auth status
            LaunchedEffect(target) {
                navController.navigate(target) {
                    popUpTo("start") { inclusive = true }
                }
            }
        }

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
            CustomerNavigationGraph(navController = navController)
        }
        composable("operator_dashboard") {
            CompanyNavigationGraph(navController = navController)
        }
    }
}
