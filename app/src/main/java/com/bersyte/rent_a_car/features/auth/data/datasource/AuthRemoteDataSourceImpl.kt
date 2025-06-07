package com.bersyte.rent_a_car.features.auth.data.datasource

import com.bersyte.rent_a_car.features.auth.data.models.AuthResponse
import com.bersyte.rent_a_car.features.auth.data.models.LoginRequest
import com.bersyte.rent_a_car.features.auth.data.models.SignUpRequest
import com.bersyte.rent_a_car.features.auth.data.services.AuthApiService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val apiService: AuthApiService
) : AuthRemoteDataSource {
    override suspend fun login(request: LoginRequest): AuthResponse {
        return apiService.login(request)
    }

    override suspend fun signUp(request: SignUpRequest): AuthResponse {
        return apiService.signup(request)
    }
}
