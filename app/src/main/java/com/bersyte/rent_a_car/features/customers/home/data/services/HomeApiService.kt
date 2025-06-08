package com.bersyte.rent_a_car.features.customers.home.data.services
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.features.customers.home.data.models.CarRating
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApiService {

    @GET("api/v1/cars/available-today")
    suspend fun getAvailableCars(): List<Car>

    @GET("api/v1/rentals/history/{plate}/total")
    suspend fun getTotalRentalsByPlate(@Path("plate") plate: String): Int

    @GET("api/v1/ratings/{plate}")
    suspend fun getCarRatings(@Path("plate") plate: String): List<CarRating>
}
