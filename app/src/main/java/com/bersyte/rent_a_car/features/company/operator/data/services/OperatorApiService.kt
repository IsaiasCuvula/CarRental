package com.bersyte.rent_a_car.features.company.operator.data.services


import com.bersyte.rent_a_car.features.company.admin.data.models.Operator
import retrofit2.http.GET

interface OperatorApiService {
    @GET("/api/v1/operators/current")
    suspend fun fetchOperator():Operator
}
