package com.bersyte.rent_a_car.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bersyte.rent_a_car.features.company.operator.ui.screens.AddCustomerScreen
import com.bersyte.rent_a_car.features.company.operator.ui.screens.CarRegistrationApprovalScreen
import com.bersyte.rent_a_car.features.company.operator.ui.screens.CreateRentalScreen
import com.bersyte.rent_a_car.features.company.operator.ui.screens.OperatorDashboardScreen
import com.bersyte.rent_a_car.features.company.operator.ui.screens.RentalManagementScreen
import com.bersyte.rent_a_car.features.company.operator.viewmodels.OperatorViewModel
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@Composable
fun OperatorNavigationGraph(
    navController: NavController,
    viewModel: OperatorViewModel = hiltViewModel()
) {
    val childNavController = rememberNavController()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchOperator {
            error -> AppHelpers.showToast(context, error)
        }
    }

    val operatorState = viewModel.selectedOperator.collectAsState()
    val operator = operatorState.value

    NavHost(
        navController = childNavController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            OperatorDashboardScreen(
                operator = operator,
                navController = navController,
                onAddCustomer = { childNavController.navigate("addCustomer") },
                onCreateRental = {},
            )
        }

        composable("addCustomer") {
            AddCustomerScreen(
                onSave = { /* Save to backend */ childNavController.popBackStack() },
                onCancel = { childNavController.popBackStack() }
            )
        }

        composable("carRegistrations") {
            CarRegistrationApprovalScreen(
                registrations = listOf(),
                onApprove = { id -> /* Approve registration */ },
                onReject = { id -> /* Reject registration */ },
                onBack = { childNavController.popBackStack() }
            )
        }

        composable("rentalRequests") {
            RentalManagementScreen(
                rentals = listOf(),
                onApprove = { code -> /* Approve rental */ },
                onReject = { code -> /* Reject rental */ },
                onBack = { childNavController.popBackStack() }
            )
        }

        composable("createRental") {
            CreateRentalScreen(
                onCancel = { childNavController.popBackStack() },
                onConfirm = { /* Logic to create rental */ }
            )
        }
    }
}
