package com.bersyte.rent_a_car.features.company.operator.data.services


import com.bersyte.rent_a_car.features.company.admin.data.models.Operator
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OperatorApiService {
    @GET("/api/v1/operators/current")
    suspend fun fetchOperator():Operator

    @POST("/api/v1/operators/create-customer")
    suspend fun createCustomer(@Body customer: CreateCustomerRequest): CreateCustomerResponse

}
