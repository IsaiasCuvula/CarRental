package com.bersyte.rent_a_car.features.auth.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.core.token.TokenManager
import com.bersyte.rent_a_car.features.auth.data.models.AuthResponse
import com.bersyte.rent_a_car.features.auth.data.models.LoginRequest
import com.bersyte.rent_a_car.features.auth.data.models.SignUpRequest
import com.bersyte.rent_a_car.features.auth.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    val tokenManager: TokenManager
) : ViewModel() {

    private val _authResponse = MutableStateFlow<AuthResponse?>(null)
    val authResponse = _authResponse.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        initState()
    }

   private fun initState() = viewModelScope.launch {
        try {
            _isLoading.value = true
            val cachedResponse = tokenManager.getAuthResponse()
            if(cachedResponse != null){
                _authResponse.value = cachedResponse
            }
        }catch (e: Exception) {
            Log.i("❌ GET INITIAL STATE", "❌ EXCEPTION: $e")
        }finally {
            _isLoading.value = false
        }
    }

    fun login(
        request : LoginRequest,
        onSuccess: (AuthResponse?) -> Unit,
        onError:(String) -> Unit
    ) = viewModelScope.launch {
        _isLoading.value = true
        try {
            val response = repository.login(request)
            tokenManager.saveAuthResponse(response)
            _authResponse.value = response
            Log.d("✅ LOGIN", " ✅ $response")
            onSuccess(response)
        }catch (e: HttpException) {
            val errorMessage = getFormattedMsg(e)
            Log.d("❌ LOGIN", "❌ ERROR BODY: $errorMessage")
            onError(errorMessage)
        }catch (e: Exception) {
            logout()
            Log.i("❌ LOGIN", "❌ EXCEPTION: $e")
            onError(e.message ?: "Unknown error occurred\n$e")
        }finally {
            _isLoading.value = false
        }
    }

    fun signup(
        request: SignUpRequest,
        onSuccess: (AuthResponse?) -> Unit,
        onError:(String) -> Unit
    ) = viewModelScope.launch {
        _isLoading.value = true
        try {
            val response = repository.signup(request)
            tokenManager.saveAuthResponse(response)
            Log.d("✅ SIGNUP", " ✅ $response")
            _authResponse.value = response
            onSuccess(response)
        }catch (e: HttpException) {
            val errorMessage = getFormattedMsg(e)
            Log.d("❌ SIGNUP", "❌ ERROR BODY: $errorMessage")
            onError(errorMessage)
        }catch (e: Exception) {
            logout()
            Log.i("❌ SIGNUP", "❌ EXCEPTION: $e")
            onError(e.message ?: "Unknown error occurred\n$e")
        }finally {
            _isLoading.value = false
        }
    }

    fun logout() {
        viewModelScope.launch {
            Log.i("LOGOUT", "LOGOUT")
            tokenManager.clearAuthResponse()
            _authResponse.value = null
        }
    }

    private fun getFormattedMsg(e: HttpException): String{
        val msg = e.response()?.errorBody()?.string()
        return try {
            if(msg != null){

                val jsonObject = JSONObject(msg)
                val message = jsonObject.getString("message")

                val nestedJsonStart = message.indexOf("{")
                val nestedJson = if (nestedJsonStart != -1) message.substring(nestedJsonStart) else null

                nestedJson?.let {
                    val nestedObject = JSONObject(it)
                    nestedObject.getString("error_description")
                } ?: message
            }else{
                "Unknown error occurred\n$msg"
            }
        } catch (ex: Exception) {
            "Unknown error occurred\n$msg"
        }
    }
}
