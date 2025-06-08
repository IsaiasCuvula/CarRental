package com.bersyte.rent_a_car.features.auth.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.common.data.models.Resource
import com.bersyte.rent_a_car.core.token.TokenManager
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
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _loginState = MutableStateFlow<Resource<AuthResponse>?>(null)
    val loginState = _loginState.asStateFlow()


    fun login(request : LoginRequest) = viewModelScope.launch {
        _loginState.value = Resource.loading()
        try {
            val response = repository.login(request)
            if (response is Resource.Success) {
                response.data?.token?.let { token ->
                    tokenManager.saveToken(token)
                }
            }
            _loginState.value = response
        } catch (e: Exception) {
            logout()
            _loginState.value = Resource.Error(e.message ?: "Log in- Unknown error occurred")
        }
    }

    fun signup(request: SignUpRequest) = viewModelScope.launch {
        _loginState.value = Resource.loading()
        try {
            val response = repository.signup(request)
            if (response is Resource.Success) {
                response.data?.token?.let { token ->
                    tokenManager.saveToken(token)
                }
            }
            _loginState.value = response
        } catch (e: Exception) {
            logout()
            _loginState.value = Resource.Error(e.message ?: "Sign up - Unknown error occurred")
        }
    }

    fun logout() {
        viewModelScope.launch {
            tokenManager.clearToken()
            _loginState.value = null
        }
    }
}
