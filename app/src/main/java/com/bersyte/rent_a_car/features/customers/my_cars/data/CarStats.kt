package com.bersyte.rent_a_car.features.customers.my_cars.data

data class CarStats(
    val totalCars: Int,
    val totalRevenue: Long,
    val activeRentals: Int,
    val avgRating: Double,
    val utilizationRate: Int,
    val totalRentals: Int
){
    companion object{

        val stats = listOf(
            CarStats(
                totalCars = 12,
                totalRevenue = 58700L,
                activeRentals = 8,
                avgRating = 4.9,
                utilizationRate = 82,
                totalRentals = 156

            ),

            CarStats(
                totalCars = 1,
                totalRevenue = 850L,
                activeRentals = 0,
                avgRating = 4.2,
                utilizationRate = 30,
                totalRentals = 3
            ),

            CarStats(
                totalCars = 12,
                totalRevenue = 58700L,
                activeRentals = 8,
                avgRating = 4.9,
                utilizationRate = 82,
                totalRentals = 156
            ),
            CarStats(
                totalCars = 3,
                totalRevenue = 6200L,
                activeRentals = 1,
                avgRating = 4.5,
                utilizationRate = 45,
                totalRentals = 19
            )

        )
    }
}
