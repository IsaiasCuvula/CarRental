package com.bersyte.rent_a_car.features.customers.rentals.data.models


data class Rental(
    val rentalCode: String,
    val carName: String,
    val carPlate: String,
    val carSeats: Int,
    val rentStartDate: String,
    val rentEndDate: String,
    val totalPaidAmount: String,
    val status: String
){

    companion object{
        val sampleRentals = listOf<Rental>(
//            Rental(
//                rentalCode = "RENT-2023-001",
//                carName = "Toyota Camry",
//                carPlate = "ABC123",
//                carSeats = 5,
//                rentStartDate = LocalDateTime.of(2023, 5, 15, 10, 0),
//                rentEndDate = LocalDateTime.of(2023, 5, 20, 10, 0),
//                totalPaidAmount = "$450.00",
//                status = RentalStatus.COMPLETED.name
//            ),
//            Rental(
//                rentalCode = "RENT-2023-002",
//                carName = "Tesla Model 3",
//                carPlate = "XYZ789",
//                carSeats = 5,
//                rentStartDate = LocalDateTime.of(2023, 6, 1, 14, 30),
//                rentEndDate = LocalDateTime.of(2023, 6, 5, 14, 30),
//                totalPaidAmount = "$600.00",
//                status = RentalStatus.ACTIVE.name
//            ),
//            Rental(
//                rentalCode = "RENT-2023-003",
//                carName = "Ford Explorer",
//                carPlate = "DEF456",
//                carSeats = 7,
//                rentStartDate = LocalDateTime.of(2023, 6, 10, 9, 0),
//                rentEndDate = LocalDateTime.of(2023, 6, 17, 9, 0),
//                totalPaidAmount = "$850.00",
//                status = RentalStatus.RESERVED.name
//            ),
//            Rental(
//                rentalCode = "RENT-2023-004",
//                carName = "Honda Civic",
//                carPlate = "GHI789",
//                carSeats = 5,
//                rentStartDate = LocalDateTime.of(2023, 5, 1, 8, 0),
//                rentEndDate = LocalDateTime.of(2023, 5, 3, 8, 0),
//                totalPaidAmount = "$210.00",
//                status = RentalStatus.CANCELLED.name
//            ),
//            Rental(
//                rentalCode = "RENT-2023-005",
//                carName = "Chevrolet Suburban",
//                carPlate = "JKL012",
//                carSeats = 8,
//                rentStartDate = LocalDateTime.of(2023, 6, 15, 12, 0),
//                rentEndDate = LocalDateTime.of(2023, 6, 22, 12, 0),
//                totalPaidAmount = "$1,200.00",
//                status = RentalStatus.RESERVED.name
//            )
        )
    }
}
