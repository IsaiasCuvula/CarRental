package com.bersyte.rent_a_car.di

import com.bersyte.rent_a_car.features.customers.home.data.datasource.HomeDatasource
import com.bersyte.rent_a_car.features.customers.home.data.repositories.HomeRepository
import com.bersyte.rent_a_car.features.customers.home.data.services.HomeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeApiService(retrofit: Retrofit): HomeApiService {
        return retrofit.create(HomeApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeDatasource(apiService: HomeApiService): HomeDatasource {
        return HomeDatasource(apiService)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(dataSource: HomeDatasource): HomeRepository {
        return HomeRepository(dataSource)
    }
}
