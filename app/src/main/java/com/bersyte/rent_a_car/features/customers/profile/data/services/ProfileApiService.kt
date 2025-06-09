package com.bersyte.rent_a_car.features.customers.profile.data.services

import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer
import com.bersyte.rent_a_car.features.customers.profile.data.models.UpdateCustomerRequest
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ProfileApiService {

    @GET("api/v1/customers/current")
    suspend fun getCustomer(): Customer

    @POST("api/v1/customers/update")
    suspend fun updateCustomer( @Body updateRequest: UpdateCustomerRequest): Customer
}
