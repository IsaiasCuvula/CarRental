package com.bersyte.rent_a_car.core.interceptor

import com.bersyte.rent_a_car.core.token.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Skip auth for login/signup
        if (request.url.encodedPath.let { path ->
                path.contains("/auth/login") || path.contains("/auth/signup")
            }) return chain.proceed(request)

        // Get token synchronously (carefully)
        val token = runBlocking { tokenManager.getAuthResponse()?.token }

        return if (token != null) {
            chain.proceed(
                request.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
            )
        } else {
            chain.proceed(request)
        }
    }
}
