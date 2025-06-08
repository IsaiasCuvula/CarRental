package com.bersyte.rent_a_car.features.company.admin.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.bersyte.rent_a_car.features.company.admin.data.AdminDashboardStats
import com.bersyte.rent_a_car.features.company.admin.data.models.Operator
import java.time.LocalDateTime

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

    val operators = listOf(
        Operator(
            id = "OP-001",
            name = "Alex Johnson",
            email = "alex.j@example.com",
            phone = "+1 (555) 123-4567",
            dateCreated = LocalDateTime.now().minusDays(30),
            isActive = true
        ),
        Operator(
            id = "OP-002",
            name = "Maria Garcia",
            email = "maria.g@example.com",
            phone = "+1 (555) 987-6543",
            dateCreated = LocalDateTime.now().minusDays(15),
            isActive = true
        ),
        Operator(
            id = "OP-003",
            name = "James Wilson",
            email = "james.w@example.com",
            phone = "+1 (555) 456-7890",
            dateCreated = LocalDateTime.now().minusDays(5),
            isActive = false
        )
    )

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
