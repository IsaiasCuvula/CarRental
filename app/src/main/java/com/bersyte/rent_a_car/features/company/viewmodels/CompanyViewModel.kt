package com.bersyte.rent_a_car.features.company.viewmodels

import android.util.Log
import com.bersyte.rent_a_car.features.company.data.models.Operator
import com.bersyte.rent_a_car.features.company.data.repositories.CompanyRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.common.data.models.CancelRental
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.company.data.models.CarRegistration
import com.bersyte.rent_a_car.features.company.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.features.company.data.models.CreateOperatorRequest
import com.bersyte.rent_a_car.features.company.data.models.CreateUserResponse
import com.bersyte.rent_a_car.features.company.data.models.FinalizeRentalRequest
import com.bersyte.rent_a_car.features.company.data.models.FinalizeRentalResponse
import com.bersyte.rent_a_car.features.company.data.models.RentingCarRequest
import com.bersyte.rent_a_car.features.company.data.models.UpdateCarRegistrationStatus
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import com.bersyte.rent_a_car.utils.enums.RegistrationStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val repository: CompanyRepository
) : ViewModel() {

    private val _operator = MutableStateFlow<Operator?>(null)
    val operator = _operator.asStateFlow()

    private val _cars = MutableStateFlow<List<Car>>(listOf())
    val cars = _cars.asStateFlow()


    private val _registrations = MutableStateFlow<List<CarRegistration>>(listOf())
    val registrations = _registrations.asStateFlow()

    private val _customers = MutableStateFlow<List<Customer>>(listOf())
    val customers = _customers.asStateFlow()

    private val _rentals = MutableStateFlow<List<Rental>>(listOf())
    val rentals = _rentals.asStateFlow()

    private val _operators = MutableStateFlow<List<Operator>>(listOf())
    val operators = _operators.asStateFlow()


    private val _operatorRentals = MutableStateFlow<List<Rental>>(listOf())
    val operatorRentals = _operatorRentals.asStateFlow()


    init {
        fetchOperator(onError = {})
        fetchAllCars(onError = {})
        fetchAllRegistrations(onError = {})
        fetchAllRentals(onError = {})
        fetchAllOperators(onError = {})
    }

     fun fetchRentalByOperator(email: String, onError:(String)-> Unit){
         viewModelScope.launch {
             try {
                 val response = repository.fetchRentalByOperator(email)
                 Log.d("FETCH_RENTALS_BY_OPERATORS", " ✅ $response")
                 _operatorRentals.value = response
             }catch (e: HttpException) {
                 val error = e.response()?.errorBody()?.string()
                 Log.d("FETCH_OPERATORS", "Error body: $error")
                 error?.let { onError(error) }
             } catch (e: Exception) {
                 Log.d("FETCH_OPERATORS", "EXCEPTION - $e")
                 e.localizedMessage?.let { onError(it) }
             }
         }
     }

    fun fetchAllOperators(onError:(String)-> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.fetchAllOperators()
                Log.d("FETCH_OPERATORS", "$response")
                _operators.value = response
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.d("FETCH_OPERATORS", "Error body: $error")
                error?.let { onError(error) }
                return@launch
            } catch (e: Exception) {
                Log.d("FETCH_OPERATORS", "EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
                return@launch
            }
        }
    }



    fun createOperator(
        operator: CreateOperatorRequest,
        onSuccess: (CreateUserResponse) -> Unit,
        onError:(String)-> Unit
    ){
        viewModelScope.launch {
            try {
                val response = repository.createOperator(operator)
                Log.d("CREATE_OPERATOR", "$response")
                onSuccess(response)
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.d("CREATE_OPERATOR", "Error body: $error")
                error?.let { onError(error) }
                return@launch
            } catch (e: Exception) {
                Log.d("CREATE_OPERATOR", "EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
                return@launch
            }
        }
    }


    fun createCustomer(
        customer: CreateCustomerRequest,
        onSuccess: (CreateUserResponse) -> Unit,
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
                return@launch
            } catch (e: Exception) {
                Log.d("CREATE_CUSTOMER", "EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
                return@launch
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

    fun fetchAllRentals(onError:(String)-> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.fetchAllRentals()
                Log.d("FETCH_RENTALS", "$response")
                _rentals.value = response
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.d("FETCH_RENTALS", "Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.d("FETCH_RENTALS", "EXCEPTION - $e")
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

    fun cancelRenting(
        rentalCode: String,
        onSuccess: (Rental?)-> Unit,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val request = CancelRental(rentalCode)
               val result = repository.cancelRental(request)
                fetchAllRentals(onError = {})
                onSuccess(result)
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.d("CANCEL_RENTAL", "HTTP Error: ${e.code()}, Body: $errorBody")
                onError(e.localizedMessage)
            }catch (e: Exception){
                Log.d("CANCEL_RENTAL", "exception: $e")
                onError(e.localizedMessage)
            }
        }
    }


    fun finalizeRental(
        request: FinalizeRentalRequest,
        onSuccess: (FinalizeRentalResponse?)-> Unit,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = repository.finalizeRental(request)
                fetchAllRentals(onError = {})
                onSuccess(result)
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.d("FINALIZE_RENTAL", "HTTP Error: ${e.code()}, Body: $errorBody")
                onError(e.localizedMessage)
            }catch (e: Exception){
                Log.d("FINALIZE_RENTAL", "exception: $e")
                onError(e.localizedMessage)
            }
        }
    }

    fun approveRental(
        request: RentingCarRequest,
        onSuccess: (Rental?)-> Unit,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = repository.approveRental(request)
                fetchAllRentals(onError = {})
                onSuccess(result)
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                Log.d("APPROVE_RENTAL", "HTTP Error: ${e.code()}, Body: $errorBody")
                onError(e.localizedMessage)
            }catch (e: Exception){
                Log.d("APPROVE_RENTAL", "exception: $e")
                onError(e.localizedMessage)
            }
        }
    }

}
