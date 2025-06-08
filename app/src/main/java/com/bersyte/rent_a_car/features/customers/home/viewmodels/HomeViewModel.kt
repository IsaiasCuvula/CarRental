package com.bersyte.rent_a_car.features.customers.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.features.customers.home.data.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadAvailableCars() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.fetchAvailableCars()
                _cars.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchTotalRentals(plate: String, onResult: (Int?) -> Unit) {
        viewModelScope.launch {
            try {
                val total = repository.getTotalRentalsByPlate(plate)
                onResult(total)
            } catch (e: Exception) {
                Log.d("TOTAL_RENTALS", "$e")
                onResult(null)
            }
        }
    }
}
