package com.bersyte.rent_a_car.features.customers.profile.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.features.customers.profile.data.models.UpdateCustomerRequest
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _userData = MutableStateFlow<Customer?>(null)
    val userData: StateFlow<Customer?> = _userData.asStateFlow()

    fun updateProfile(updateRequest: UpdateCustomerRequest) {
        viewModelScope.launch {
            try {
                // Call your API or repository
               // val updatedUser = userRepository.updateProfile(updateRequest)
                //_userData.value = updatedUser
                // Show success message
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
