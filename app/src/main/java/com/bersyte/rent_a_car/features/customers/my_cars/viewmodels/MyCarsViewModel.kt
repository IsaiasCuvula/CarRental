package com.bersyte.rent_a_car.features.customers.my_cars.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.customers.my_cars.data.repositories.MyCarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class MyCarsViewModel @Inject constructor(
    private val repository: MyCarsRepository
) : ViewModel() {

    private val _saveResult = MutableStateFlow<List<Car>>(listOf())
    val saveResult = _saveResult.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    init {
        getAllCars()
    }

     private fun getAllCars() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val cars = repository.getAllCars()
                _saveResult.value = cars
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.d("GET_CARS", "$errorBody")
            } catch (e: Exception) {
                val error = e.localizedMessage ?: "Unknown error occurred"
                Log.e("GET_CARS", "exception: $error")
                Log.e("GET_CARS", "exception: $e")
            } finally {
                _loading.value = false
            }
        }
    }

    fun saveCar(carRequest: CarRequest, onSuccess: (Car?)-> Unit, onError: (String?) -> Unit) {
        viewModelScope.launch {
           try {
              val result = repository.saveCar(carRequest)
               onSuccess(result)
               getAllCars()
           } catch (e: HttpException) {
               val errorBody = e.response()?.errorBody()?.string()
               Log.d("SAVE_CAR", "HTTP Error: ${e.code()}, Body: $errorBody")
               onError(e.localizedMessage)
           }catch (e: Exception){
               Log.d("SAVE_CAR", "exception: $e")
               onError(e.localizedMessage)
           }
        }
    }
}
