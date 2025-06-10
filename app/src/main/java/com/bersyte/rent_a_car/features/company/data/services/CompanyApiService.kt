package com.bersyte.rent_a_car.features.company.data.services


import com.bersyte.rent_a_car.common.data.models.CancelRental
import com.bersyte.rent_a_car.common.data.models.Car
import com.bersyte.rent_a_car.common.data.models.CarRequest
import com.bersyte.rent_a_car.features.company.data.models.CarRegistration
import com.bersyte.rent_a_car.features.company.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.features.company.data.models.CreateOperatorRequest
import com.bersyte.rent_a_car.features.company.data.models.CreateUserResponse
import com.bersyte.rent_a_car.features.company.data.models.FinalizeRentalRequest
import com.bersyte.rent_a_car.features.company.data.models.FinalizeRentalResponse
import com.bersyte.rent_a_car.features.company.data.models.Operator
import com.bersyte.rent_a_car.features.company.data.models.RentingCarRequest
import com.bersyte.rent_a_car.features.company.data.models.UpdateCarRegistrationStatus
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CompanyApiService {

    @GET("api/v1/rentals/history/{email}")
    suspend fun fetchRentalByOperator(@Path("email") email: String): List<Rental>

    @POST("api/v1/admin/create-operator")
    suspend fun createOperator(@Body request: CreateOperatorRequest): CreateUserResponse

    @POST("api/v1/rentals/renting")
    suspend fun approveRental(@Body request: RentingCarRequest): Rental

    @POST("api/v1/rentals/finalize")
    suspend fun finalizeRental(@Body request: FinalizeRentalRequest): FinalizeRentalResponse

    @POST("api/v1/rentals/cancel")
    suspend fun cancelRental(@Body request: CancelRental): Rental

    @GET("api/v1/rentals/history")
    suspend fun fetchAllRentals(): List<Rental>

    @POST("api/v1/car-registrations/update-status")
    suspend fun updateRegistration(@Body request: UpdateCarRegistrationStatus): CarRegistration

    @GET("api/v1/car-registrations")
    suspend fun fetchAllRegistrations(): List<CarRegistration>

    @POST("api/v1/car-registrations")
    suspend fun registerCar(@Body carRequest: CarRequest): Car

    @GET("/api/v1/operators/current")
    suspend fun fetchOperator(): Operator

    @GET("/api/v1/customers")
    suspend fun fetchAllCustomers(): List<Customer>

    @GET("/api/v1/operators")
    suspend fun fetchAllOperators(): List<Operator>

    @GET("/api/v1/cars")
    suspend fun fetchAllCars(): List<Car>

    @POST("/api/v1/operators/create-customer")
    suspend fun createCustomer(@Body customer: CreateCustomerRequest): CreateUserResponse

}
