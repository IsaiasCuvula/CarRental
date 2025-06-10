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
                id = 1,
                description = "Scratch on left rear door",
                estimatedRepairCost = 250,
                isPaid = true,
                paidAt = LocalDateTime.now().minusDays(2),
                reportedAt = LocalDateTime.now().minusDays(5),
                fixedAt = LocalDateTime.now().minusDays(1),
                damageLocation = "Left rear door",
                rentalId = 101,
                carId = 202,
                markAsPaidByEmployeeId = 303,
                markAsFixedEmployeeId = 303,
                status = VehicleDamageStatus.FIXED
            ),
            VehicleDamage(
                id = 2,
                description = "Dent on front bumper",
                estimatedRepairCost = 400,
                isPaid = false,
                paidAt = LocalDateTime.now(),
                reportedAt = LocalDateTime.now().minusDays(1),
                fixedAt = LocalDateTime.now(),
                damageLocation = "Front bumper",
                rentalId = 101,
                carId = 202,
                markAsPaidByEmployeeId = null,
                markAsFixedEmployeeId = null,
                status = VehicleDamageStatus.PENDING
            )
        )
    }
}
