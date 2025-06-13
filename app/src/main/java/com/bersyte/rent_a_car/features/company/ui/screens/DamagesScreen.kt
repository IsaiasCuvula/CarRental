package com.bersyte.rent_a_car.features.company.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.ui.components.ScrollableFilterChips
import com.bersyte.rent_a_car.common.ui.components.VerticalSpace
import com.bersyte.rent_a_car.features.company.ui.components.damage.DamageCard
import com.bersyte.rent_a_car.features.company.ui.components.damage.EmptyState
import com.bersyte.rent_a_car.features.company.viewmodels.VehicleDamagesViewModel
import com.bersyte.rent_a_car.utils.enums.VehicleDamageStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DamagesScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    viewModel: VehicleDamagesViewModel = hiltViewModel()
) {
    val damages by viewModel.damages.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val filterDamageOptions = VehicleDamageStatus.getAllFilterOptions()
    var selectedDamageStatus by remember { mutableStateOf("All") }

    val filteredDamages = remember(damages, selectedDamageStatus) {
        when (selectedDamageStatus) {
            "All" -> damages
            else -> {
                val status = VehicleDamageStatus.fromDisplayName(selectedDamageStatus)
                damages.filter { damage ->
                    VehicleDamageStatus.valueOf(damage.status) == status
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vehicle Damages") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ){
            ScrollableFilterChips(
                options = filterDamageOptions,
                selectedIndex = filterDamageOptions.indexOf(selectedDamageStatus),
                onSelected = { index ->
                    selectedDamageStatus = if (filterDamageOptions[index] == selectedDamageStatus) {
                        "All"
                    } else {
                        filterDamageOptions[index]
                    }
                }
            )

            VerticalSpace()
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (damages.isEmpty()) {
                    EmptyState()
                } else {
                    LazyColumn(
                        modifier = modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredDamages) { damage ->
                            DamageCard(damage = damage)
                        }
                    }
                }
            }
        }
    }
}
