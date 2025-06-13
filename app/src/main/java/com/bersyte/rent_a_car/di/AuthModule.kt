package com.bersyte.rent_a_car.di

import com.bersyte.rent_a_car.features.auth.data.repositories.AuthRepository
import com.bersyte.rent_a_car.features.auth.data.repositories.AuthRepositoryImpl
import com.bersyte.rent_a_car.features.auth.data.services.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }


    @Provides
    fun provideAuthRepository(apiService: AuthApiService): AuthRepository {
        return AuthRepositoryImpl(apiService)
    }
}
