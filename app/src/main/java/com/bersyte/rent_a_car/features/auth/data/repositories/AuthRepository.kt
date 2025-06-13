package com.bersyte.rent_a_car.features.auth.data.repositories

import com.bersyte.rent_a_car.features.auth.data.models.AuthResponse
import com.bersyte.rent_a_car.features.auth.data.models.LoginRequest
import com.bersyte.rent_a_car.features.auth.data.models.SignUpRequest

interface AuthRepository {
    suspend fun login(request: LoginRequest): AuthResponse
    suspend fun signup(request: SignUpRequest): AuthResponse
}
