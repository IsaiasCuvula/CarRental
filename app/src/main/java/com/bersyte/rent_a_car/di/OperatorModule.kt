package com.bersyte.rent_a_car.di

import com.bersyte.rent_a_car.features.company.data.repositories.CompanyRepository
import com.bersyte.rent_a_car.features.company.data.services.OperatorApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OperatorModule {

    @Provides
    @Singleton
    fun provideOperatorApiService(retrofit: Retrofit): OperatorApiService {
        return retrofit.create(OperatorApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOperatorRepository(apiService: OperatorApiService): CompanyRepository {
        return CompanyRepository(apiService)
    }
}
