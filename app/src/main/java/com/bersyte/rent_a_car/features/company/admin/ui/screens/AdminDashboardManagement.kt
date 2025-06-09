package com.bersyte.rent_a_car.features.company.admin.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bersyte.rent_a_car.features.company.admin.data.AdminDashboardStats
import com.bersyte.rent_a_car.features.company.admin.data.models.Operator

@Composable
fun AdminDashboardManagement() {
    val dashboardStats = AdminDashboardStats(
        totalUsers = 124,
        totalCars = 56,
        activeRentals = 18,
        revenueLast30Days = 24500,
        newUsersLast30Days = 24,
        availableCars = 38
    )

    val operators = listOf<Operator>()

    var showAddOperator by remember { mutableStateOf(false) }

    if (showAddOperator) {
        AddOperatorScreen(
            onSave = { newOperator ->
                // Add to your operators list
                showAddOperator = false
            },
            onCancel = { showAddOperator = false }
        )
    } else {
        AdminDashboardScreen(
            stats = dashboardStats,
            operators = operators,
            onAddOperator = { showAddOperator = true },
            onOperatorClick = { operator ->
                // Handle operator click (edit/view details)
            }
        )
    }
}
