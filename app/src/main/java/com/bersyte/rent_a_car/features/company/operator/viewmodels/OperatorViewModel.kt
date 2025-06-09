package com.bersyte.rent_a_car.features.company.operator.viewmodels

import android.util.Log
import com.bersyte.rent_a_car.features.company.admin.data.models.Operator
import com.bersyte.rent_a_car.features.company.operator.data.repositories.OperatorRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerResponse
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class OperatorViewModel @Inject constructor(
    private val repository: OperatorRepository
) : ViewModel() {

    private val _operator = MutableStateFlow<Operator?>(null)
    val operator = _operator.asStateFlow()

    init {
        fetchOperator(onError = {})
        fetchAllCars(onError = {}, onSuccess = {})
    }

    fun createCustomer(
        customer: CreateCustomerRequest,
        onSuccess: (CreateCustomerResponse) -> Unit,
        onError:(String)-> Unit
    ){
        viewModelScope.launch {
            try {
                val response = repository.createCustomer(customer)
                Log.d("CREATE_CUSTOMER", "$response")
                onSuccess(response)
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.d("CREATE_CUSTOMER", "Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.d("CREATE_CUSTOMER", "EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }
        }
    }

    fun fetchOperator(onError:(String)-> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.fetchOperator()
                _operator.value = response
                Log.d("FETCH_OPERATOR", "$response")
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.d("FETCH_OPERATOR", "Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.d("FETCH_OPERATOR", "EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }
        }
    }


    fun fetchAllCustomers(onSuccess: (List<Customer>) -> Unit, onError:(String)-> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.fetchAllCustomers()
                Log.d("FETCH_CUSTOMERS", "$response")
                onSuccess(response)
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.d("FETCH_CUSTOMERS", "Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.d("FETCH_CUSTOMERS", "EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }
        }
    }

    fun fetchAllCars(onSuccess: (List<Car>) -> Unit, onError:(String)-> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.fetchAllCars()
                Log.d("FETCH_CARS", "$response")
                onSuccess(response)
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.d("FETCH_CARS", "Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.d("FETCH_CARS", "EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }
        }
    }

    fun registerCar(carRequest: CarRequest, onSuccess: (Car?)-> Unit, onError: (String?) -> Unit) {
        viewModelScope.launch {
            try {
                val result = repository.registerCar(carRequest)
                onSuccess(result)
                fetchAllCars(onError = {}, onSuccess = {})
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.d("SAVE_CAR_OPERATOR", "HTTP Error: ${e.code()}, Body: $errorBody")
                onError(e.localizedMessage)
            }catch (e: Exception){
                Log.d("SAVE_CAR_OPERATOR", "exception: $e")
                onError(e.localizedMessage)
            }
        }
    }


}
