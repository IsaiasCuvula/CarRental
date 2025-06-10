package com.bersyte.rent_a_car.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bersyte.rent_a_car.R
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import com.bersyte.rent_a_car.utils.enums.UserRole
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.filled.Phone

@Composable
fun CustomerCard(
    customer: Customer,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(12.dp)
    ){
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(16.dp),
           verticalAlignment = Alignment.CenterVertically
       ) {
           Box(
               modifier = Modifier
                   .size(56.dp)
           ){
                 AsyncImage(
                     model = "https://i.pravatar.cc/150?img=${customer.name.hashCode() % 70}",
                     contentDescription = "Customer Avatar",
                     modifier = Modifier
                         .size(56.dp)
                         .clip(CircleShape),
                     contentScale = ContentScale.Crop,
                     placeholder = painterResource(id = R.drawable.car_rental),
                     error = painterResource(id = R.drawable.car_rental)
                 )
             }

                 Spacer(modifier = Modifier.size(16.dp))

                 // Customer Details
                 Column(
                 modifier = Modifier.weight(1f)
                 ) {
             Row(
                 modifier = Modifier.fillMaxWidth(),
                 horizontalArrangement = Arrangement.SpaceBetween
             ) {
                 Text(
                     text = customer.name,
                     style = MaterialTheme.typography.titleMedium,
                     fontWeight = FontWeight.Bold,
                     maxLines = 1,
                     overflow = TextOverflow.Ellipsis
                 )

                 // Role badge
                 Box(
                     modifier = Modifier
                         .clip(RoundedCornerShape(8.dp))
                         .background(
                             when (customer.role) {
                                 UserRole.ADMIN -> MaterialTheme.colorScheme.errorContainer
                                 UserRole.OPERATOR -> MaterialTheme.colorScheme.tertiaryContainer
                                 else -> MaterialTheme.colorScheme.secondaryContainer
                             }
                         )
                         .padding(horizontal = 8.dp, vertical = 4.dp)
                 ) {
                     Text(
                         text = customer.role.name,
                         style = MaterialTheme.typography.labelSmall,
                         color = when (customer.role) {
                             UserRole.ADMIN -> MaterialTheme.colorScheme.onErrorContainer
                             UserRole.OPERATOR -> MaterialTheme.colorScheme.onTertiaryContainer
                             else -> MaterialTheme.colorScheme.onSecondaryContainer
                         }
                     )
                 }
             }

             Spacer(modifier = Modifier.height(4.dp))

             CustomerDetailRow(
                 icon = Icons.Default.Person,
                 text = customer.email
             )

             if(customer.phone !=null){
                CustomerDetailRow(
                    icon = Icons.Default.Phone,
                    text = customer.phone
                )
             }


             Spacer(modifier = Modifier.height(8.dp))


             Text(
                 text = "${customer.loyaltyPoints} loyalty points",
                 style = MaterialTheme.typography.labelMedium,
                 color = MaterialTheme.colorScheme.primary,
                 fontWeight = FontWeight.Medium
             )
          }
       }
    }

}
