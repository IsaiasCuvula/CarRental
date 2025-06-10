package com.bersyte.rent_a_car.features.company.data.repositories

import com.bersyte.rent_a_car.common.data.models.CancelRental
import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.company.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.features.company.data.models.CreateOperatorRequest
import com.bersyte.rent_a_car.features.company.data.models.FinalizeRentalRequest
import com.bersyte.rent_a_car.features.company.data.models.RentingCarRequest
import com.bersyte.rent_a_car.features.company.data.models.UpdateCarRegistrationStatus
import com.bersyte.rent_a_car.features.company.data.services.CompanyApiService
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val apiService: CompanyApiService
) {
    suspend fun fetchOperator() = apiService.fetchOperator()
    suspend fun createCustomer(customer: CreateCustomerRequest) = apiService.createCustomer(customer)
    suspend fun fetchAllCustomers() = apiService.fetchAllCustomers()
    suspend fun fetchAllCars() = apiService.fetchAllCars()
    suspend fun registerCar(carRequest: CarRequest) = apiService.registerCar(carRequest)
    suspend fun fetchAllRegistrations()= apiService.fetchAllRegistrations()
    suspend fun updateRegistration(request: UpdateCarRegistrationStatus)= apiService.updateRegistration(request)
    suspend fun fetchAllRentals() = apiService.fetchAllRentals()
    suspend fun fetchAllOperators() = apiService.fetchAllOperators()

    suspend fun approveRental(request: RentingCarRequest)= apiService.approveRental(request)
    suspend fun finalizeRental(request: FinalizeRentalRequest)= apiService.finalizeRental(request)
    suspend fun cancelRental(request: CancelRental)= apiService.cancelRental(request)
    suspend fun createOperator(request: CreateOperatorRequest)= apiService.createOperator(request)
}
