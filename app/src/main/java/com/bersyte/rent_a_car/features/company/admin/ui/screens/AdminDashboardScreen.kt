package com.bersyte.rent_a_car.features.company.admin.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bersyte.rent_a_car.features.company.admin.data.AdminDashboardStats
import com.bersyte.rent_a_car.features.company.admin.data.models.Operator
import com.bersyte.rent_a_car.features.company.admin.ui.components.DashboardTab
import com.bersyte.rent_a_car.features.company.admin.ui.components.OperatorsTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    stats: AdminDashboardStats,
    operators: List<Operator>,
    onAddOperator: () -> Unit,
    onOperatorClick: (Operator) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Dashboard", "Operators")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Admin Dashboard") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            if (selectedTab == 1) {
                FloatingActionButton(
                    onClick = onAddOperator,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Operator")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab (
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }
            when (selectedTab) {
                0 -> DashboardTab(stats = stats)
                1 -> OperatorsTab(
                    operators = operators,
                    onOperatorClick = onOperatorClick
                )
            }
        }
    }
}
