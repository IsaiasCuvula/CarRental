package com.bersyte.rent_a_car.features.company.operator.data.repositories

import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.features.company.operator.data.services.OperatorApiService
import javax.inject.Inject

class OperatorRepository @Inject constructor(
    private val apiService: OperatorApiService
) {
    suspend fun fetchOperator() = apiService.fetchOperator()
    suspend fun createCustomer(customer: CreateCustomerRequest) = apiService.createCustomer(customer)
    suspend fun fetchAllCustomers() = apiService.fetchAllCustomers()
}
