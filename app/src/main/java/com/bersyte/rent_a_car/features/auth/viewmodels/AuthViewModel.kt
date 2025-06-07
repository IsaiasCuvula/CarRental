package com.bersyte.rent_a_car.features.auth.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.common.data.models.Resource
import com.bersyte.rent_a_car.features.auth.data.models.AuthResponse
import com.bersyte.rent_a_car.features.auth.data.models.LoginRequest
import com.bersyte.rent_a_car.features.auth.data.models.SignUpRequest
import com.bersyte.rent_a_car.features.auth.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<AuthResponse>?>(null)
    val loginState = _loginState.asStateFlow()

    fun login(request : LoginRequest) {
        viewModelScope.launch {
            _loginState.value = Resource.loading()
            _loginState.value = repository.login(request)
        }
    }

    fun signup(request: SignUpRequest) {
        viewModelScope.launch {
            _loginState.value = Resource.loading()
            _loginState.value = repository.signup(request)
        }
    }

    fun resetLoginState() {
        _loginState.value = null
    }
}
