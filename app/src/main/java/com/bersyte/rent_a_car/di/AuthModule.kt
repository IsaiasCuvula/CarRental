package com.bersyte.rent_a_car.di

import com.bersyte.rent_a_car.features.auth.data.datasource.AuthRemoteDataSource
import com.bersyte.rent_a_car.features.auth.data.datasource.AuthRemoteDataSourceImpl
import com.bersyte.rent_a_car.features.auth.data.repositories.AuthRepository
import com.bersyte.rent_a_car.features.auth.data.repositories.AuthRepositoryImpl
import com.bersyte.rent_a_car.features.auth.data.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {
    @Provides
    fun provideAuthRemoteDataSource(apiService: ApiService): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl(apiService)
    }

    @Provides
    fun provideAuthRepository(remoteDataSource: AuthRemoteDataSource): AuthRepository {
        return AuthRepositoryImpl(remoteDataSource)
    }
}
