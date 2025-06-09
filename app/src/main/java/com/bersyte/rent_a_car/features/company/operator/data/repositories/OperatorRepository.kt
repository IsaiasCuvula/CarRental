package com.bersyte.rent_a_car.features.company.operator.data.repositories

import com.bersyte.rent_a_car.features.company.admin.data.models.Operator
import com.bersyte.rent_a_car.features.company.operator.data.services.OperatorApiService
import javax.inject.Inject

class OperatorRepository @Inject constructor(
    private val apiService: OperatorApiService
) {
    suspend fun fetchOperator() = apiService.fetchOperator()
}
