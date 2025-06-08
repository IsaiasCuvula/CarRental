package com.bersyte.rent_a_car.features.customers.home.data.services
import com.bersyte.rent_a_car.common.data.models.Car
import retrofit2.http.GET

interface HomeApiService {

    @GET("api/v1/cars/available-today")
    suspend fun getAvailableCars(): List<Car>
}
