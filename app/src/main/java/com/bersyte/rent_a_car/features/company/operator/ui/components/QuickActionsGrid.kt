package com.bersyte.rent_a_car.features.company.operator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuickActionsGrid(
    onAddCustomer: () -> Unit,
    onCreateRental: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            ActionCard(
                title = "Add Customer",
                icon = Icons.Default.PersonAdd,
                onClick = onAddCustomer
            )
        }
        item {
            ActionCard(
                title = "Create Rental",
                icon = Icons.Default.AddShoppingCart,
                onClick = onCreateRental
            )
        }
    }
}
