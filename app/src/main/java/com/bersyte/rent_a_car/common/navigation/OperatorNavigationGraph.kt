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
import com.bersyte.rent_a_car.features.auth.viewmodels.AuthViewModel
import com.bersyte.rent_a_car.features.company.ui.screens.AddCustomerScreen
import com.bersyte.rent_a_car.features.company.ui.screens.AddOperatorScreen
import com.bersyte.rent_a_car.features.company.ui.screens.CarRegistrationsScreen
import com.bersyte.rent_a_car.features.company.ui.screens.CarsScreen
import com.bersyte.rent_a_car.features.company.ui.screens.CustomersScreen
import com.bersyte.rent_a_car.features.company.ui.screens.DashboardScreen
import com.bersyte.rent_a_car.features.company.ui.screens.OperatorsScreen
import com.bersyte.rent_a_car.features.company.ui.screens.RentalManagementScreen
import com.bersyte.rent_a_car.features.company.viewmodels.CompanyViewModel
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@Composable
fun OperatorNavigationGraph(
    navController: NavController,
    viewModel: CompanyViewModel = hiltViewModel(),
    authVM: AuthViewModel = hiltViewModel()
) {
    val childNavController = rememberNavController()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchOperator {
            error -> AppHelpers.showToast(context, error)
        }
    }

    val operatorState = viewModel.operator.collectAsState()
    val operator = operatorState.value

    NavHost(
        navController = childNavController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            DashboardScreen(
                operator = operator,
                navController = childNavController,
                onLogout = {
                    authVM.logout()
                    navController.navigate("login") {
                        popUpTo("profile") { inclusive = true }
                    }
                }
            )
        }

        composable("addCustomer") {
            AddCustomerScreen(
                onSave = { customerRequest ->
                    viewModel.createCustomer(
                        customerRequest, onSuccess = {customer ->

                            if(customer.email.isNotBlank()){
                                AppHelpers.showToast(context,
                                    "Customer Created Successfully"
                                )
                                childNavController.popBackStack()
                            }
                        },
                        onError = {error->
                            AppHelpers.showToast(context, "Something ent wrong \n$error")
                        }
                    )

                },
                onCancel = { childNavController.popBackStack() }
            )
        }

        composable("addOperator") {
            AddOperatorScreen(
                onSave = { operatorRequest ->
                    viewModel.createOperator(
                        operatorRequest, onSuccess = {customer ->

                            if(customer.email.isNotBlank()){
                                AppHelpers.showToast(context,
                                    "Operator Created Successfully"
                                )
                                childNavController.popBackStack()
                            }
                        },
                        onError = {error->
                            AppHelpers.showToast(context, "Something ent wrong \n$error")
                        }
                    )

                },
                onCancel = { childNavController.popBackStack() }
            )
        }

        composable("carRegistrations") {
            CarRegistrationsScreen(
                onBack = { childNavController.popBackStack() }
            )
        }

        composable("rentalRequests") {
            RentalManagementScreen(
                onBack = { childNavController.popBackStack() }
            )
        }

        composable("createRental") {
            CarsScreen(
                onCancel = { childNavController.popBackStack() },
            )
        }

        composable("customers") {
            CustomersScreen(
                onCancel = {childNavController.popBackStack()},
                onAddCustomer = { childNavController.navigate("addCustomer") },
            )
        }

        composable("operators") {
            OperatorsScreen(
                onCancel = {childNavController.popBackStack()},
                onAddOperator = { childNavController.navigate("addOperator") },
            )
        }
    }
}
