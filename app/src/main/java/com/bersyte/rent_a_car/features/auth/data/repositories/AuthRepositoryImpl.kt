package com.bersyte.rent_a_car.features.auth.data.repositories

import android.util.Log
import com.bersyte.rent_a_car.common.data.models.Resource
import com.bersyte.rent_a_car.features.auth.data.datasource.AuthRemoteDataSource
import com.bersyte.rent_a_car.features.auth.data.models.AuthResponse
import com.bersyte.rent_a_car.features.auth.data.models.LoginRequest
import com.bersyte.rent_a_car.features.auth.data.models.SignUpRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun login(request: LoginRequest): Resource<AuthResponse> {
        return try {
            val response = remoteDataSource.login(request)
            Log.d("LOGIN-RESPONSE", "login - response: $response")
            Resource.Success(response)
        } catch (e: Exception) {
            Log.e("LOGIN", "login exception: $e")
            Log.d("LOGIN", "login exception: ${e.localizedMessage}")
            Resource.Error(e.localizedMessage ?: "Login An unknown error occurred")
        }
    }

    override suspend fun signup(request: SignUpRequest): Resource<AuthResponse> {
        return try {
            val response = remoteDataSource.signUp(request)
            Resource.Success(response)
        } catch (e: Exception) {
            Log.e("SIGNUP", "signup exception: $e")
            Log.d("SIGNUP", "signup exception: ${e.localizedMessage}")
            Resource.Error(e.localizedMessage ?: "Sign up An unknown error occurred")
        }
    }
}
