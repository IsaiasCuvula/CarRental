package com.bersyte.rent_a_car.features.customers.rentals.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.bersyte.rent_a_car.utils.enums.RentalStatus
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.features.customers.rentals.data.models.CarRatingRequest
import com.bersyte.rent_a_car.features.customers.rentals.viewmodels.RentalViewModel
import com.bersyte.rent_a_car.utils.helpers.AppHelpers


@Composable
fun RentalCard(
    rental: Rental,
    onCancel: (Rental) -> Unit,
    viewModel: RentalViewModel = hiltViewModel()
) {
    val rentalStatus = RentalStatus.valueOf(rental.status)
    var showReviewSheet by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val borderColor = when (rentalStatus) {
        RentalStatus.ACTIVE -> Color(0xFF4CAF50)
        RentalStatus.COMPLETED -> Color(0xFF2196F3)
        RentalStatus.CANCELLED -> Color(0xFFF44336)
        else -> Color(0xFFFFC107)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, borderColor.copy(alpha = 0.5f)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = rental.carName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                SuggestionChip(
                    label = {
                        Text(
                            text = rental.status,
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = borderColor.copy(alpha = 0.2f),
                        labelColor = borderColor
                    ),
                    onClick = {},
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconTextPair(
                    icon = Icons.Default.DirectionsCar,
                    text = rental.carPlate
                )
                IconTextPair(
                    icon = Icons.Default.People,
                    text = "${rental.carSeats} seats"
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "From",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = AppHelpers.formatDateTime(rental.rentStartDate),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "To",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = AppHelpers.formatDateTime(rental.rentEndDate),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            )

            Text(
                text = "Code: ${rental.rentalCode}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

           Row(
               modifier = Modifier.fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(
                   text = "Total: ${rental.formattedAmount}",
                   style = MaterialTheme.typography.titleMedium,
                   fontWeight = FontWeight.SemiBold,
                   color = MaterialTheme.colorScheme.primary
               )

               if(rentalStatus == RentalStatus.RESERVED){
                   Spacer(modifier = Modifier.weight(1f))

                   Button(onClick = { onCancel(rental) }) {
                       Text(
                           text = "Cancel",
                       )
                   }
               }

               if(rentalStatus == RentalStatus.COMPLETED){
                   Spacer(modifier = Modifier.weight(1f))

                   SuggestionChip(
                       label = {
                           Text(
                               text = "Rate car",
                               style = MaterialTheme.typography.labelSmall
                           )
                       },
                       colors = SuggestionChipDefaults.suggestionChipColors(
                           containerColor = borderColor.copy(alpha = 0.2f),
                           labelColor = borderColor
                       ),
                       onClick = {
                           showReviewSheet = true
                       },
                   )
                }
               }
           }
        }

    if (showReviewSheet) {
        ReviewBottomSheet(
            onDismiss = { showReviewSheet = false },
            onSubmitReview = { rating, comment ->
                val request = CarRatingRequest(
                     rating,
                     comment,
                     rental.carPlate,
                     rental.rentalCode,
                )
                viewModel.addReview(request,
                    onSuccess = {savedRating ->
                        if(savedRating != null){
                            showReviewSheet = false
                            AppHelpers.showToast(context, "Review saved successfully")
                        }
                    },
                    onError = {error->
                        AppHelpers.showToast(context, "Something went wrong $error")
                    }
                )
            }
        )
    }
}
