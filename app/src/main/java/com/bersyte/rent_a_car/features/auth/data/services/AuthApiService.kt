package com.bersyte.rent_a_car.features.auth.data.services

import com.bersyte.rent_a_car.features.auth.data.models.AuthResponse
import com.bersyte.rent_a_car.features.auth.data.models.LoginRequest
import com.bersyte.rent_a_car.features.auth.data.models.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("/auth/signup")
    suspend fun signup(@Body request: SignUpRequest): AuthResponse
}
