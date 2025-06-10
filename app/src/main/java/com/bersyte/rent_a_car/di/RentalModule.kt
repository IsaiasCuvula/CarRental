package com.bersyte.rent_a_car.di

import com.bersyte.rent_a_car.features.customers.rentals.data.repositories.RentalRepository
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
    fun provideRentalRepository(apiService: RentalsApiService): RentalRepository {
        return RentalRepository(apiService)
    }
}
