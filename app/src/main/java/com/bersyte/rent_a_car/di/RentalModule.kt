package com.bersyte.rent_a_car.di

import com.bersyte.rent_a_car.features.customers.rentals.data.datasource.RentalsRemoteDataSource
import com.bersyte.rent_a_car.features.customers.rentals.data.repositories.RentalRepository
import com.bersyte.rent_a_car.features.customers.rentals.data.repositories.RentalRepositoryImpl
import com.bersyte.rent_a_car.features.customers.rentals.data.services.RentalsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RentalModule {

    @Provides
    @Singleton
    fun provideRentalsApiService(retrofit: Retrofit): RentalsApiService {
        return retrofit.create(RentalsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRentalRemoteDataSource(apiService: RentalsApiService): RentalsRemoteDataSource {
        return RentalsRemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideRentalRepository(
        dataSource: RentalsRemoteDataSource
    ): RentalRepository {
        return RentalRepositoryImpl(dataSource)
    }
}
