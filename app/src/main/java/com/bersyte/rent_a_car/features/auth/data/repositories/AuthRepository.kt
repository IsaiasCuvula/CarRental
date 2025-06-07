package com.bersyte.rent_a_car.features.auth.data.repositories

import com.bersyte.rent_a_car.common.data.models.Resource
import com.bersyte.rent_a_car.features.auth.data.models.AuthResponse
import com.bersyte.rent_a_car.features.auth.data.models.LoginRequest
import com.bersyte.rent_a_car.features.auth.data.models.SignUpRequest
import javax.inject.Inject

interface AuthRepository {
    suspend fun login(request: LoginRequest): Resource<AuthResponse>
    suspend fun signup(request: SignUpRequest): Resource<AuthResponse>
}
