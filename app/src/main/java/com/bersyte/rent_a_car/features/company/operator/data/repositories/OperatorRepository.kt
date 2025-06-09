package com.bersyte.rent_a_car.features.company.operator.data.repositories

import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.features.company.operator.data.services.OperatorApiService
import retrofit2.http.Body
import javax.inject.Inject

class OperatorRepository @Inject constructor(
    private val apiService: OperatorApiService
) {
    suspend fun fetchOperator() = apiService.fetchOperator()
    suspend fun createCustomer(customer: CreateCustomerRequest) = apiService.createCustomer(customer)
    suspend fun fetchAllCustomers() = apiService.fetchAllCustomers()
    suspend fun fetchAllCars() = apiService.fetchAllCars()
    suspend fun registerCar(carRequest: CarRequest) = apiService.registerCar(carRequest)
}
