package com.bersyte.rent_a_car.features.customers.home.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CarDetailsGrid(
    year: Int,
    seats: Int,
    carType: String,
    fuelType: String
) {
    val items = listOf(
        DetailItem("Year", year.toString()),
        DetailItem("Seats", seats.toString()),
        DetailItem("Type", carType),
        DetailItem("Fuel", fuelType)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items) { item ->
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Text(
                    text = item.value,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

data class DetailItem(val title: String, val value: String)
