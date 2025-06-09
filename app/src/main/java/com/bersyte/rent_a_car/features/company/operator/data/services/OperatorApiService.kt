package com.bersyte.rent_a_car.features.company.operator.data.services


import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.company.admin.data.models.Operator
import com.bersyte.rent_a_car.features.company.operator.data.models.CarRegistration
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerResponse
import com.bersyte.rent_a_car.features.company.operator.data.models.UpdateCarRegistrationStatus
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OperatorApiService {

    @GET("api/v1/rentals/history")
    suspend fun fetchAllRentals(): List<Rental>

    @POST("api/v1/car-registrations/update-status")
    suspend fun updateRegistration(@Body request: UpdateCarRegistrationStatus): CarRegistration

    @GET("api/v1/car-registrations")
    suspend fun fetchAllRegistrations(): List<CarRegistration>

    @POST("api/v1/car-registrations")
    suspend fun registerCar(@Body carRequest: CarRequest): Car

    @GET("/api/v1/operators/current")
    suspend fun fetchOperator():Operator

    @GET("/api/v1/customers")
    suspend fun fetchAllCustomers(): List<Customer>

    @GET("/api/v1/cars")
    suspend fun fetchAllCars(): List<Car>

    @POST("/api/v1/operators/create-customer")
    suspend fun createCustomer(@Body customer: CreateCustomerRequest): CreateCustomerResponse

}
