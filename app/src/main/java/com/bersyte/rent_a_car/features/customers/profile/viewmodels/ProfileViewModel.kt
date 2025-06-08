package com.bersyte.rent_a_car.features.customers.profile.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.features.customers.profile.data.models.UpdateCustomerRequest
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.bersyte.rent_a_car.features.customers.profile.data.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _customer = MutableStateFlow<Customer?>(null)
    val customer: StateFlow<Customer?> = _customer

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchCustomer();
    }

   private fun fetchCustomer() {
        viewModelScope.launch {
            try {
                val response = repository.getCustomer()
                _customer.value = response
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun updateCustomer(request: UpdateCustomerRequest) {
        viewModelScope.launch {
            try {
                val response = repository.updateCustomer(request)
                _customer.value = response
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
