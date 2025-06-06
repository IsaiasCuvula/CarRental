package com.bersyte.rent_a_car.features.customers.profile.data

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val joinDate: String,
    val profileImage: String? = null,
    val totalRentals: Int = 0,
    val loyaltyPoints: Int = 0
){
    companion object{
        val sampleUser = User(
            id = "USR-12345",
            name = "John Doe",
            email = "john.doe@example.com",
            phone = "+1 (555) 123-4567",
            joinDate = "May 2022",
            profileImage = "https://example.com/profile.jpg",
            totalRentals = 12,
            loyaltyPoints = 850
        )
    }
}
