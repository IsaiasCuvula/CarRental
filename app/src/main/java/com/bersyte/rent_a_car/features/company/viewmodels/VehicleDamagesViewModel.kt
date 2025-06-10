package com.bersyte.rent_a_car.features.company.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.features.company.data.models.VehicleDamage
import com.bersyte.rent_a_car.utils.enums.VehicleDamageStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class VehicleDamagesViewModel : ViewModel() {
    private val _damages = MutableStateFlow<List<VehicleDamage>>(emptyList())
    val damages: StateFlow<List<VehicleDamage>> = _damages

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadDamages()
    }

    private fun loadDamages() {
        viewModelScope.launch {
            _isLoading.value = true
            // In a real app, you would fetch from repository/API here
            _damages.value = sampleDamages()
            _isLoading.value = false
        }
    }

    private fun sampleDamages(): List<VehicleDamage> {
        return listOf(
            VehicleDamage(
                description = "Scratch on left rear door",
                estimatedRepairCost = 250,
                isPaid = true,
                paidAt = LocalDateTime.now().minusDays(2).toString(),
                reportedAt = LocalDateTime.now().minusDays(5).toString(),
                fixedAt = LocalDateTime.now().minusDays(1).toString(),
                damageLocation = "Left rear door",
                rentalCode = "101",
                carPlate = "202",
                markAsPaidBy = "303",
                markAsFixed = 303,
                status = VehicleDamageStatus.FIXED.name
            ),
            VehicleDamage(
                description = "Dent on front bumper",
                estimatedRepairCost = 400,
                isPaid = false,
                paidAt = LocalDateTime.now().toString(),
                reportedAt = LocalDateTime.now().minusDays(1).toString(),
                fixedAt = LocalDateTime.now().toString(),
                damageLocation = "Front bumper",
                rentalCode = "104",
                carPlate = "202",
                markAsPaidBy = null,
                markAsFixed = null,
                status = VehicleDamageStatus.PENDING.name
            )
        )
    }
}
