package com.bersyte.rent_a_car.features.customers.home.data.datasource
import com.bersyte.rent_a_car.features.customers.home.data.models.ReservationRequest
import com.bersyte.rent_a_car.features.customers.home.data.services.HomeApiService
import javax.inject.Inject

class HomeDatasource @Inject constructor(
    private val api: HomeApiService
) {
    suspend fun getAvailableCars() = api.getAvailableCars()
    suspend fun getTotalRentalsByPlate(plate: String) = api.getTotalRentalsByPlate(plate)
    suspend fun getCarRatings(plate: String) = api.getCarRatings(plate)
    suspend fun reserveCar(request: ReservationRequest) = api.reserveCar(request)
}
