package com.bersyte.rent_a_car.common.data.models

import com.bersyte.rent_a_car.features.customers.home.data.models.Address
import com.bersyte.rent_a_car.features.customers.home.data.models.CarRating
import com.bersyte.rent_a_car.features.customers.rentals.data.models.Rental
import java.time.LocalDateTime

data class Car(
  val color: Int,
  val smokingAllowed: Boolean,
  val seats: Int,
  val discountPercentage: Int,
  val hourlyPrice: Long,
  val feePerHourRented: Long,
  val carClass: String,
  val carType: String,
  val carStatus: String,
  val fuelType: String,
  val photos: String,
  val name: String,
  val description: String,
  val model: String,
  val year: Int,
  val plate: String,
  val address: Address,
  val ratings: List<CarRating>,
  val rentals: List<Rental>,
  val createdAt: LocalDateTime
){

    companion object {
      val data  =  listOf(
        Car(
        color = 0,
        smokingAllowed = false,
        seats = 5,
        discountPercentage = 10,
        hourlyPrice = 25,
        feePerHourRented = 2,
        carClass = "Standard",
        carType = "Sedan",
        carStatus = "Available",
        fuelType = "Gasoline",
        photos = "car1.jpg",
        name = "Comfort Plus",
        description = "A comfortable sedan with great mileage and modern features.",
        model = "Toyota Camry",
        year = 2022,
        plate = "ABC123",
        address = Address("123 Main St", "New York", "NY"),
        ratings = listOf(CarRating(4.5), CarRating(5.0), CarRating(4.0)),
        createdAt = LocalDateTime.now(),
        rentals = Rental.sampleRentals
        ),

        Car(
        color = 0,
        smokingAllowed = false,
        seats = 5,
        discountPercentage = 10,
        hourlyPrice = 25,
        feePerHourRented = 2,
        carClass = "Standard",
        carType = "Sedan",
        carStatus = "Available",
        fuelType = "Gasoline",
        photos = "car1.jpg",
        name = "Comfort Plus",
        description = "A comfortable sedan with great mileage and modern features.",
        model = "Toyota Camry",
        year = 2022,
        plate = "ABC123",
        address = Address("123 Main St", "New York", "NY"),
        ratings = listOf(CarRating(4.5), CarRating(5.0), CarRating(4.0)),
        createdAt = LocalDateTime.now(),
                rentals = Rental.sampleRentals
        ),

        Car(
        color = 0,
        smokingAllowed = false,
        seats = 5,
        discountPercentage = 10,
        hourlyPrice = 25,
        feePerHourRented = 2,
        carClass = "Standard",
        carType = "Sedan",
        carStatus = "Available",
        fuelType = "Gasoline",
        photos = "car1.jpg",
        name = "Comfort Plus",
        description = "A comfortable sedan with great mileage and modern features.",
        model = "Toyota Camry",
        year = 2022,
        plate = "ABC123",
        address = Address("123 Main St", "New York", "NY"),
        ratings = listOf(CarRating(4.5), CarRating(5.0), CarRating(4.0)),
        createdAt = LocalDateTime.now(),
          rentals = Rental.sampleRentals
        ),


        Car(
        color = 0,
        smokingAllowed = false,
        seats = 5,
        discountPercentage = 10,
        hourlyPrice = 25,
        feePerHourRented = 2,
        carClass = "Standard",
        carType = "Sedan",
        carStatus = "Available",
        fuelType = "Gasoline",
        photos = "car1.jpg",
        name = "Comfort Plus",
        description = "A comfortable sedan with great mileage and modern features.",
        model = "Toyota Camry",
        year = 2022,
        plate = "ABC123",
        address = Address("123 Main St", "New York", "NY"),
        ratings = listOf(CarRating(4.5), CarRating(5.0), CarRating(4.0)),
        createdAt = LocalDateTime.now(),
          rentals = Rental.sampleRentals
        ),

        Car(
        color = 0,
        smokingAllowed = false,
        seats = 5,
        discountPercentage = 10,
        hourlyPrice = 25,
        feePerHourRented = 2,
        carClass = "Standard",
        carType = "Sedan",
        carStatus = "Available",
        fuelType = "Gasoline",
        photos = "car1.jpg",
        name = "Comfort Plus",
        description = "A comfortable sedan with great mileage and modern features.",
        model = "Toyota Camry",
        year = 2022,
        plate = "ABC123",
        address = Address("123 Main St", "New York", "NY"),
        ratings = listOf(CarRating(4.5), CarRating(5.0), CarRating(4.0)),
        createdAt = LocalDateTime.now(),
          rentals = Rental.sampleRentals
        ),

        Car(
        color = 0,
        smokingAllowed = false,
        seats = 5,
        discountPercentage = 10,
        hourlyPrice = 25,
        feePerHourRented = 2,
        carClass = "Standard",
        carType = "Sedan",
        carStatus = "Available",
        fuelType = "Gasoline",
        photos = "car1.jpg",
        name = "Comfort Plus",
        description = "A comfortable sedan with great mileage and modern features.",
        model = "Toyota Camry",
        year = 2022,
        plate = "ABC123",
        address = Address("123 Main St", "New York", "NY"),
        ratings = listOf(CarRating(4.5), CarRating(5.0), CarRating(4.0)),
        createdAt = LocalDateTime.now(),
          rentals = Rental.sampleRentals
        ),
        )
    }
}
