package com.bersyte.rent_a_car.features.customers.profile.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.features.customers.profile.data.models.UpdateCustomerRequest
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.bersyte.rent_a_car.features.customers.profile.data.repositories.ProfileRepository
import com.bersyte.rent_a_car.utils.helpers.AppHelpers
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _customer = MutableStateFlow<Customer?>(null)
    val customer: StateFlow<Customer?> = _customer

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchCustomer() {
        viewModelScope.launch {
            try {
                val response = repository.getCustomer()
                _customer.value = response
                Log.d("FETCH_CUSTOMER", "$response")
            } catch (e: Exception) {
                Log.d("FETCH_CUSTOMER", "EXCEPTION - $e")
                _error.value = e.message
            }
        }
    }

    fun updateCustomer(
        request: UpdateCustomerRequest,
        onSuccess: (Customer?) -> Unit,
        onError: (String)-> Unit
    ) {
        Log.d("UPDATE_CUSTOMER", "$request")
        viewModelScope.launch {
            try {
                val response = repository.updateCustomer(request)
                _customer.value = response
                Log.d("UPDATE_CUSTOMER", "$response")
                onSuccess(response)
            }catch (e: HttpException) {
                val error = AppHelpers.extractErrorMsg(e)
                Log.d("UPDATE_CUSTOMER", "Error body: $error")
                _error.value = e.message
                onError(error)
            } catch (e: Exception) {
                Log.d("UPDATE_CUSTOMER", "EXCEPTION - $e")
                _error.value = e.message
            }
        }
    }
}
