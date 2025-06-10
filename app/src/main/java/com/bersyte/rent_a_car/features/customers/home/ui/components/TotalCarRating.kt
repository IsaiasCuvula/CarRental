package com.bersyte.rent_a_car.features.customers.home.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.features.customers.home.viewmodels.HomeViewModel
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@Composable
fun TotalCarRatings(
    plate: String,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val ratingsState = viewModel.ratings.collectAsState()
    val ratings = ratingsState.value

    var totalRatings by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(plate) {
        viewModel.getCarRatings(plate)
        totalRatings = if (ratings.isEmpty()) {
            0.0
        } else {
            AppHelpers.calculateRoundedRatingAverage(ratings)
        }
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Rating",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$totalRatings",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
