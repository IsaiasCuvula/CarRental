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

    fun saveCar(carRequest: CarRequest, onSuccess: (Car?)-> Unit, onError: (String?) -> Unit) {
        viewModelScope.launch {
           try {
              val result = repository.saveCar(carRequest)
               onSuccess(result)
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
