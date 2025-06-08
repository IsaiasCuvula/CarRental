package com.bersyte.rent_a_car.features.auth.viewmodels

import android.util.Log
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

    private val _authResponse = MutableStateFlow<Resource<AuthResponse>?>(null)
    val authResponse = _authResponse.asStateFlow()

    init {
        viewModelScope.launch {
            val cachedResponse = tokenManager.getAuthResponse()
            _authResponse.value = cachedResponse?.let { Resource.Success(it) }
        }
    }


    fun login(request : LoginRequest) = viewModelScope.launch {
        _authResponse.value = Resource.loading()
        try {
            val response = repository.login(request)
            if (response is Resource.Success) {
                response.data?.let { data ->
                    Log.i("LOGIN SUCCESS", "SAVE DATA: $data")
                    tokenManager.saveAuthResponse(data)
                    _authResponse.value = response
                }
            }
        } catch (e: Exception) {
            logout()
            Log.i("LOGIN EXCEPTION", "Exception: $e")
            _authResponse.value = Resource.Error(e.message ?: "Log in- Unknown error occurred")
        }
    }

    fun signup(request: SignUpRequest) = viewModelScope.launch {
        _authResponse.value = Resource.loading()
        try {
            val response = repository.signup(request)
            if (response is Resource.Success) {
                response.data?.let { data ->
                    Log.i("SIGNUP SUCCESS", "SAVE DATA: $data")
                    tokenManager.saveAuthResponse(data)
                    _authResponse.value = response
                }
            }
        } catch (e: Exception) {
            logout()
            Log.i("SIGNUP EXCEPTION", "Exception: $e")
            _authResponse.value = Resource.Error(e.message ?: "Sign up - Unknown error occurred")
        }
    }

    fun logout() {
        viewModelScope.launch {
            Log.i("LOGOUT", "LOGOUT")
            tokenManager.clearAuthResponse()
            _authResponse.value = null
        }
    }
}
