package com.bersyte.rent_a_car.di

import com.bersyte.rent_a_car.features.company.data.repositories.CompanyRepository
import com.bersyte.rent_a_car.features.company.data.services.CompanyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CompanyModule {

    @Provides
    @Singleton
    fun provideCompanyApiService(retrofit: Retrofit): CompanyApiService {
        return retrofit.create(CompanyApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCompanyRepository(apiService: CompanyApiService): CompanyRepository {
        return CompanyRepository(apiService)
    }
}
