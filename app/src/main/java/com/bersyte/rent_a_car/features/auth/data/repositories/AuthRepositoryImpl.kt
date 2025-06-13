package com.bersyte.rent_a_car.features.auth.data.repositories

import com.bersyte.rent_a_car.features.auth.data.models.LoginRequest
import com.bersyte.rent_a_car.features.auth.data.models.SignUpRequest
import com.bersyte.rent_a_car.features.auth.data.services.AuthApiService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthApiService
) : AuthRepository {

    override suspend fun login(request: LoginRequest) = apiService.login(request)
    override suspend fun signup(request: SignUpRequest)= apiService.signup(request)
}
