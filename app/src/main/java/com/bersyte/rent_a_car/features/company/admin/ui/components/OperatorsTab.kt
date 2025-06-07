package com.bersyte.rent_a_car.features.company.admin.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.features.company.admin.data.Operator


@Composable
fun OperatorsTab(
    operators: List<Operator>,
    onOperatorClick: (Operator) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(operators) { operator ->
            OperatorCard(
                operator = operator,
                onClick = { onOperatorClick(operator) }
            )
        }
    }
}
