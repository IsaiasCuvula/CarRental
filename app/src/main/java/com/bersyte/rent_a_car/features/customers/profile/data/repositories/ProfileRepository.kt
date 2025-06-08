package com.bersyte.rent_a_car.features.customers.profile.data.repositories

import com.bersyte.rent_a_car.features.customers.profile.data.datasource.ProfileDataSource
import com.bersyte.rent_a_car.features.customers.profile.data.models.UpdateCustomerRequest
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val dataSource: ProfileDataSource
) {
    suspend fun getCustomer() = dataSource.getCustomer()

    suspend fun updateCustomer(request: UpdateCustomerRequest) =
        dataSource.updateCustomer(request)
}
