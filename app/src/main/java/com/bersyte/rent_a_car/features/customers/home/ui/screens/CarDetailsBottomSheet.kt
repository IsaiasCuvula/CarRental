package com.bersyte.rent_a_car.features.customers.home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.common.data.models.Car
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.R
import com.bersyte.rent_a_car.common.ui.components.RatingItem
import com.bersyte.rent_a_car.common.ui.components.VerticalSpace
import com.bersyte.rent_a_car.features.customers.home.ui.components.CarDetailsGrid
import com.bersyte.rent_a_car.features.customers.home.ui.components.TotalCarRatings
import com.bersyte.rent_a_car.features.customers.home.viewmodels.HomeViewModel
import com.bersyte.rent_a_car.utils.helpers.AppHelpers


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetailsBottomSheet(
    car: Car,
    onDismiss: () -> Unit,
    onReserveClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
     val plate = car.plate

    LaunchedEffect(plate) {
        viewModel.getCarRatings(plate)
    }

    val ratingsState = viewModel.ratings.collectAsState()
    val ratings = ratingsState.value

    val pricePerHour = AppHelpers.centsToUsd(car.hourlyPrice)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.car_holder),
                contentDescription = "${car.model} image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .height(120.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "${car.name} ${car.model}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    text = "$pricePerHour/hour",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.weight(1f))

                TotalCarRatings(car)
            }

            CarDetailsGrid(
                year = car.year,
                seats = car.seats,
                carType = car.carType,
                fuelType = car.fuelType
            )

            VerticalSpace()

            Text(
                text = car.description,
                style = MaterialTheme.typography.bodyMedium
            )
            VerticalSpace()
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            LazyColumn {
                items(ratings) { rating ->
                    RatingItem(rating = rating)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = onReserveClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reserve Now")
            }
        }
    }

}
