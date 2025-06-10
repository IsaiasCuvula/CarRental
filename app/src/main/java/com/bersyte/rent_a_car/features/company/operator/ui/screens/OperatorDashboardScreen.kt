package com.bersyte.rent_a_car.features.company.operator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Workspaces
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.company.operator.ui.components.WelcomeCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.bersyte.rent_a_car.features.company.admin.data.models.Operator
import com.bersyte.rent_a_car.features.company.operator.ui.components.ActionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperatorDashboardScreen(
    operator: Operator?,
    modifier: Modifier = Modifier,
    navController: NavController,
    onLogout:()-> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Operator Dashboard") },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = onLogout
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Logout")
                    }
                }
            )
        }
    ) { innerPadding ->
       Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

           operator?.name?.let { WelcomeCard(operatorName = it) }

           Text(
               text = "Quick Actions",
               style = MaterialTheme.typography.titleLarge,
               modifier = Modifier.padding(top = 8.dp)
           )

           Row(
               horizontalArrangement = Arrangement.spacedBy(16.dp),
               modifier = Modifier.fillMaxWidth()
           ) {
               ActionCard(
                   title = "Customers",
                   icon = Icons.Default.People,
                   onClick = {
                       navController.navigate("customers")
                   },
                   modifier = Modifier.weight(1f)
               )
               ActionCard(
                   title = "Cars",
                   icon = Icons.Default.PersonAdd,
                   onClick = {
                       navController.navigate("createRental")
                   },
                   modifier = Modifier.weight(1f)
               )
           }

           Row(
               horizontalArrangement = Arrangement.spacedBy(16.dp),
               modifier = Modifier.fillMaxWidth()
           ) {
               ActionCard(
                   title = "Rentals",
                   icon = Icons.Default.CarRental,
                   onClick = {
                       navController.navigate("rentalRequests")
                   },
                   modifier = Modifier.weight(1f)
               )
               ActionCard(
                   title = "Registrations",
                   icon = Icons.Default.AppRegistration,
                   onClick = {
                       navController.navigate("carRegistrations")
                   },
                   modifier = Modifier.weight(1f)
               )
           }

           Row(
               horizontalArrangement = Arrangement.spacedBy(16.dp),
               modifier = Modifier.fillMaxWidth()
           ) {
               ActionCard(
                   title = "Operators",
                   icon = Icons.Default.Workspaces,
                   onClick = {
                       navController.navigate("operators")
                   },
                   modifier = Modifier.weight(1f)
               )
               ActionCard(
                   title = "Registrations",
                   icon = Icons.Default.AppRegistration,
                   onClick = {
                       navController.navigate("carRegistrations")
                   },
                   modifier = Modifier.weight(1f)
               )
           }
        }
    }
}
