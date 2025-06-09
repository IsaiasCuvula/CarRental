package com.bersyte.rent_a_car.features.company.operator.viewmodels

import android.util.Log
import com.bersyte.rent_a_car.features.company.admin.data.models.Operator
import com.bersyte.rent_a_car.features.company.operator.data.repositories.OperatorRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.company.operator.data.models.CarRegistration
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerResponse
import com.bersyte.rent_a_car.features.company.operator.data.models.UpdateCarRegistrationStatus
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import com.bersyte.rent_a_car.utils.enums.RegistrationStatus
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

    private val _cars = MutableStateFlow<List<Car>>(listOf())
    val cars = _cars.asStateFlow()


    private val _registrations = MutableStateFlow<List<CarRegistration>>(listOf())
    val registrations = _registrations.asStateFlow()

    private val _customers = MutableStateFlow<List<Customer>>(listOf())
    val customers = _customers.asStateFlow()



    init {
        fetchOperator(onError = {})
        fetchAllCars(onError = {})
        fetchAllRegistrations(onError = {})
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


    fun fetchAllCustomers(onError:(String)-> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.fetchAllCustomers()
                Log.d("FETCH_CUSTOMERS", "$response")
                _customers.value = response
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

    fun fetchAllCars(onError:(String)-> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.fetchAllCars()
                Log.d("FETCH_CARS", "$response")
                _cars.value = response
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
                fetchAllCars(onError = {})
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

    fun fetchAllRegistrations(onError:(String)-> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.fetchAllRegistrations()
                Log.d("FETCH_CAR_REGISTRATIONS", "$response")
                _registrations.value = response
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.d("FETCH_CAR_REGISTRATIONS", "Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.d("FETCH_CAR_REGISTRATIONS", "EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }
        }
    }


    fun rejectRegistration(
        registrationNumber: String,
        plate: String,
        onSuccess: (CarRegistration?)-> Unit,
        onError: (String?) -> Unit
    ) {
        val request = UpdateCarRegistrationStatus(
            registrationNumber , plate, RegistrationStatus.REJECTED.name
        )
        updateRegistration(
            onSuccess = onSuccess, onError = onError,
            request = request
        )
    }

    fun approveRegistration(
        registrationNumber: String,
        plate: String,
        onSuccess: (CarRegistration?)-> Unit,
        onError: (String?) -> Unit
    ) {
        val request = UpdateCarRegistrationStatus(
            registrationNumber , plate, RegistrationStatus.APPROVED.name
        )
        updateRegistration(
            onSuccess = onSuccess, onError = onError,
            request = request
        )
    }


   private fun updateRegistration(
       request: UpdateCarRegistrationStatus,
       onSuccess: (CarRegistration?)-> Unit,
       onError: (String?) -> Unit
   ) {
        viewModelScope.launch {
            try {
                val result = repository.updateRegistration(request)
                fetchAllRegistrations(onError = {})
                onSuccess(result)
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.d("UPDATE_CAR_REGISTRATION", "HTTP Error: ${e.code()}, Body: $errorBody")
                onError(e.localizedMessage)
            }catch (e: Exception){
                Log.d("UPDATE_CAR_REGISTRATION", "exception: $e")
                onError(e.localizedMessage)
            }
        }
    }

}
