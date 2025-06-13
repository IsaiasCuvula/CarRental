package com.bersyte.rent_a_car.common.ui.components.car

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SmokingAllowedToggle(
    isSmokingAllowed: Boolean,
    onSmokingAllowedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Smoking Allowed")
        Switch(
            checked = isSmokingAllowed,
            onCheckedChange = onSmokingAllowedChanged
        )
    }
}
