package com.bersyte.rent_a_car.features.customers.home.data.datasource
import com.bersyte.rent_a_car.features.customers.home.data.services.HomeApiService
import javax.inject.Inject

class HomeDatasource @Inject constructor(
    private val api: HomeApiService
) {
    suspend fun getAvailableCars() = api.getAvailableCars()
}
