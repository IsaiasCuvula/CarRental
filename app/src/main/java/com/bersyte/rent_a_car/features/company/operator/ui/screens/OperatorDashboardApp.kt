package com.bersyte.rent_a_car.features.company.operator.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun OperatorDashboardApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            OperatorDashboardScreen(
                operatorName = "John Operator",
                onAddCustomer = { navController.navigate("addCustomer") },
                onViewCustomers = { navController.navigate("customers") },
                onViewCarRegistrations = { navController.navigate("carRegistrations") },
                onViewRentalRequests = { navController.navigate("rentalRequests") },
                onCreateRental = { navController.navigate("createRental") },
                onViewCars = { navController.navigate("cars") },
                onViewRentals = { navController.navigate("rentals") }
            )
        }

        composable("addCustomer") {
            AddCustomerScreen(
                onSave = { /* Save to backend */ navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }

        composable("carRegistrations") {
            CarRegistrationApprovalScreen(
                registrations = listOf(),
                onApprove = { id -> /* Approve registration */ },
                onReject = { id -> /* Reject registration */ },
                onBack = { navController.popBackStack() }
            )
        }

        composable("rentalRequests") {
            RentalManagementScreen(
                rentals = listOf(),
                onApprove = { code -> /* Approve rental */ },
                onReject = { code -> /* Reject rental */ },
                onBack = { navController.popBackStack() }
            )
        }

        composable("createRental") {
            CreateRentalScreen(
                onCancel = { navController.popBackStack() },
                onConfirm = { /* Logic to create rental */ }
            )
        }
    }
}
