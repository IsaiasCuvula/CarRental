package com.bersyte.rent_a_car.features.customers.my_cars.data.repositories

import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.customers.my_cars.data.services.MyCarsApiService
import javax.inject.Inject

class MyCarsRepository @Inject constructor(
    private val myCarsApiService: MyCarsApiService
) {
    suspend fun registerCar(carRequest: CarRequest)= myCarsApiService.registerCar(carRequest)
    suspend fun getAllCars()= myCarsApiService.getAllCars()
    suspend fun getAllRentals() = myCarsApiService.fetchCustomerRentals()
}
