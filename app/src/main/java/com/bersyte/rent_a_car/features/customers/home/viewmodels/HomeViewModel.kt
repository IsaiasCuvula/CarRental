package com.bersyte.rent_a_car.features.customers.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.features.customers.home.data.models.CarRating
import com.bersyte.rent_a_car.features.customers.home.data.models.ReservationRequest
import com.bersyte.rent_a_car.features.customers.home.data.repositories.HomeRepository
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import com.bersyte.rent_a_car.utils.helpers.AppHelpers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars = _cars.asStateFlow()

    private val _ratings = MutableStateFlow<List<CarRating>>(emptyList())
    val ratings = _ratings.asStateFlow()

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


    fun getCarRatings(plate: String) {
        viewModelScope.launch {
            try {
                _ratings.value = repository.getCarRatings(plate)
            } catch (e: Exception) {
                Log.d("TOTAL_RATINGS", "$e")
                _ratings.value = listOf()
            }
        }
    }

     fun reserveCar(request :ReservationRequest, onResult: (Rental?) -> Unit) {
         viewModelScope.launch {
             val result = try {
                 Log.d("RESERVING_CAR", "Request: $request")
                 val response = repository.reserveCar(request)
                 Log.d("RESERVING_CAR", "RESULT: $response")
                response
             } catch (e: HttpException) {
                 val errorMessage = AppHelpers.extractErrorMsg(e)
                 Log.d("RESERVING_CAR", "HTTP Error: ${e.code()}, $errorMessage")
                 null
             } catch (e: Exception) {
                 Log.d("RESERVING_CAR", "Error: ${e.message}")
                 null
             }

             withContext(Dispatchers.Main) {
                 onResult(result)
             }
         }
     }
}
