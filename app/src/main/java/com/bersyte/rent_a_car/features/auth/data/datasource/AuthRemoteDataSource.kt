package com.bersyte.rent_a_car.features.auth.data.datasource

import com.bersyte.rent_a_car.features.auth.data.models.AuthResponse
import com.bersyte.rent_a_car.features.auth.data.models.LoginRequest
import com.bersyte.rent_a_car.features.auth.data.models.SignUpRequest

interface AuthRemoteDataSource {
    suspend fun login(request: LoginRequest): AuthResponse
    suspend fun signUp(request: SignUpRequest): AuthResponse
}
