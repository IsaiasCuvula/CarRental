package com.bersyte.rent_a_car.di


import com.bersyte.rent_a_car.features.customers.my_cars.data.repositories.MyCarsRepository
import com.bersyte.rent_a_car.features.customers.my_cars.data.services.MyCarsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyCarsModule {

    @Provides
    @Singleton
    fun provideMyCarsService(retrofit: Retrofit): MyCarsApiService {
        return retrofit.create(MyCarsApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideMyCarsRepository(
        myCarsApiService: MyCarsApiService
    ): MyCarsRepository {
        return MyCarsRepository(myCarsApiService)
    }
}
