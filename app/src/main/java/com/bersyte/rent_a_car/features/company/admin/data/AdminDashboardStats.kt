package com.bersyte.rent_a_car.features.company.admin.data

data class AdminDashboardStats(
    val totalUsers: Int,
    val totalCars: Int,
    val activeRentals: Int,
    val revenueLast30Days: Long,
    val newUsersLast30Days: Int,
    val availableCars: Int
)
