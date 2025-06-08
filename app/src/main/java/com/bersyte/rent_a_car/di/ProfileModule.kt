package com.bersyte.rent_a_car.di

import com.bersyte.rent_a_car.features.customers.profile.data.datasource.ProfileDataSource
import com.bersyte.rent_a_car.features.customers.profile.data.repositories.ProfileRepository
import com.bersyte.rent_a_car.features.customers.profile.data.services.ProfileApiService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileApiService(retrofit: Retrofit): ProfileApiService {
        return retrofit.create(ProfileApiService::class.java)
    }

    @Provides
    fun provideProfileDataSource(apiService: ProfileApiService): ProfileDataSource {
        return ProfileDataSource(apiService)
    }

    @Provides
    fun provideProfileRepository(dataSource: ProfileDataSource): ProfileRepository {
        return ProfileRepository(dataSource)
    }
}
