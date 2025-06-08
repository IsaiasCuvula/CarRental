package com.bersyte.rent_a_car.features.customers.home.data.services
import com.bersyte.rent_a_car.common.data.models.Car
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApiService {

    @GET("api/v1/cars/available-today")
    suspend fun getAvailableCars(): List<Car>

    @GET("api/v1/rentals/history/{plate}/total")
    suspend fun getTotalRentalsByPlate(@Path("plate") plate: String): Int
}
