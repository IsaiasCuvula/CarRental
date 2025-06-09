package com.bersyte.rent_a_car.features.customers.rentals.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.common.data.models.CancelRental
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import com.bersyte.rent_a_car.features.customers.rentals.data.repositories.RentalRepository
import com.bersyte.rent_a_car.features.customers.rentals.data.models.PostponeRentalRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentalViewModel @Inject constructor(
    private val rentalRepository: RentalRepository
) : ViewModel() {

    var rentals by mutableStateOf<List<Rental>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    private var postponedRental by mutableStateOf<Rental?>(null)
    private var cancelRental by mutableStateOf<Rental?>(null)

    init {
        fetchRentals()
    }

    private fun fetchRentals() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                rentals = rentalRepository.getAllRentals()
            } catch (e: Exception) {
                error = e.localizedMessage
            } finally {
                isLoading = false
            }
        }
    }

     fun postponeRental(request: PostponeRentalRequest) {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                postponedRental = rentalRepository.postponeRental(request)
                fetchRentals()
            } catch (e: Exception) {
                error = e.localizedMessage
            } finally {
                isLoading = false
            }
        }
    }

    fun cancelRenting(request: CancelRental) {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                cancelRental = rentalRepository.cancelRenting(request)
                fetchRentals()
            } catch (e: Exception) {
                Log.d("CANCEL RENT", "EXCEPTION: $e")
                error = e.localizedMessage
            } finally {
                isLoading = false
            }
        }
    }
}
