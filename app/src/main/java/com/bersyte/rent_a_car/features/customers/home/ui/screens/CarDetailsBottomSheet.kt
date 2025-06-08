package com.bersyte.rent_a_car.features.customers.home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.common.data.models.Car
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.bersyte.rent_a_car.R
import com.bersyte.rent_a_car.features.customers.home.ui.components.CarDetailsGrid
import com.bersyte.rent_a_car.features.customers.home.ui.components.DisplayCarRating
import com.bersyte.rent_a_car.features.customers.home.ui.components.ShowDatePickerDialog
import com.bersyte.rent_a_car.utils.helpers.AppHelpers


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetailsBottomSheet(
    car: Car,
    onDismiss: () -> Unit,
    onReserveClick: () -> Unit
) {
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
            // Car Image Gallery (using photos string)
            Image(
                painter = painterResource(id = R.drawable.car_holder),
                contentDescription = "${car.model} image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .height(200.dp)
            )
//            AsyncImage(
//                model = car.photos.split(",").firstOrNull(),
//                contentDescription = car.name,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .clip(RoundedCornerShape(8.dp))
//            )

            Spacer(Modifier.height(16.dp))

            // Car Title
            Text(
                text = "${car.name} ${car.model}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            // Price and Rating Row
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

                DisplayCarRating(plate = car.plate)
            }

            // Details Grid
            CarDetailsGrid(
                year = car.year,
                seats = car.seats,
                carType = car.carType,
                fuelType = car.fuelType
            )

            Spacer(Modifier.height(16.dp))

            // Description
            Text(
                text = car.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(24.dp))

            // Reserve Button
            Button(
                onClick = onReserveClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reserve Now")
            }
        }
    }

}
