package com.bersyte.rent_a_car.features.company.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.common.ui.components.CommonTextField


@Composable
fun EnterInitialConditions(
    onSave: (String) -> Unit
) {

    var initialConditions by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Enter Initial Car Conditions",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        CommonTextField(
            value = initialConditions,
            onValueChange = { initialConditions = it },
            label = "Conditions description",
            isError = initialConditions.isBlank()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button (
            onClick = { onSave(initialConditions) },
            enabled = initialConditions.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save and Approve")
        }
    }

}
