package com.bersyte.rent_a_car.features.customers.home.data.repositories

import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.features.customers.home.data.datasource.HomeDatasource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val dataSource: HomeDatasource
) {
    suspend fun fetchAvailableCars(): List<Car> {
        return dataSource.getAvailableCars()
    }

    suspend fun getTotalRentalsByPlate(plate: String) = dataSource.getTotalRentalsByPlate(plate)
    suspend fun getCarRatings(plate: String) = dataSource.getCarRatings(plate)
}
