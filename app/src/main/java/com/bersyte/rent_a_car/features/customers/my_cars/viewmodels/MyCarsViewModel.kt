package com.bersyte.rent_a_car.features.customers.my_cars.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.customers.my_cars.data.repositories.MyCarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyCarsViewModel @Inject constructor(
    private val repository: MyCarsRepository
) : ViewModel() {

    private val _saveResult = MutableStateFlow<Car?>(null)
    val saveResult: StateFlow<Car?> = _saveResult

    fun saveCar(carRequest: CarRequest) {
        viewModelScope.launch {
           try {
               _saveResult.value = repository.saveCar(carRequest)
           }catch (e: Exception){
               Log.d("SAVE_CAR", "exception: $e")
           }
        }
    }

}
