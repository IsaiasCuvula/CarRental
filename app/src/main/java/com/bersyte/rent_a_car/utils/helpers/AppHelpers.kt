package com.bersyte.rent_a_car.utils.helpers

import android.content.Context
import android.widget.Toast
import com.bersyte.rent_a_car.common.data.models.CarRequest
import java.time.Instant
import java.time.LocalDateTime
import java.time.Year
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object AppHelpers{

    fun formatDateTime(dateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a")
        return dateTime.format(formatter)
    }

    fun validateAndSave(
        context: Context,
        name: String,
        model: String,
        plate: String,
        selectedSeats: String,
        hourlyPrice: String,
        carClass: String,
        carType: String,
        fuelType: String,
        smokingAllowed: Boolean,
        mileage: String,
        description: String,
        cityName: String,
        street: String,
        state: String,
        selectedColor: Int,
        selectedYear: String
    ): CarRequest? {

            // Validate all required string fields
            val validatedName = validateStringField(context, name, "Car name") ?: return null
            val validatedModel = validateStringField(context, model, "Model") ?: return null
            val validatedPlate = validateStringField(context, plate, "License plate") ?: return null


            // Validate numeric fields
            val validatedSeats = validateNumberField(context, selectedSeats, "Seats", 1, 10) ?: return null
            val validatedHourlyPrice = validateNumberField(context, hourlyPrice, "Hourly price", 1, 10000) ?: return null
            val validatedMileage = validateNumberField(context, mileage, "Mileage", 0, 1000000) ?: return null
            val validatedYear = validateNumberField(context, selectedYear, "Year", 1900, Year.now().value) ?: return null

            // Validate other required selections
            if (carClass.isBlank()) {
                showToast(context, "Please select a car class")
                return null
            }

            if (carType.isBlank()) {
                showToast(context, "Please select a car type")
                return null
            }

            if (fuelType.isBlank()) {
                showToast(context, "Please select a fuel type")
                return null
            }

            val validatedCity = validateStringField(context, cityName, "City") ?: return null
            val validatedStreet = validateStringField(context, street, "City") ?: return null
            val validatedState = validateStringField(context, state, "City") ?: return null

            return CarRequest(
                color = selectedColor,
                smokingAllowed = smokingAllowed,
                seats = validatedSeats,
                hourlyPrice = validatedHourlyPrice.toLong(),
                carClass = carClass,
                carType = carType,
                fuelType = fuelType,
                name = validatedName,
                description = description,
                model = validatedModel,
                year = validatedYear,
                plate = validatedPlate,
                mileage = validatedMileage.toLong(),
                cityName = validatedCity,
                street = validatedStreet,
                state = validatedState
            )
    }

    private fun validateNumberField(
        context: Context,
        value: String?,
        fieldName: String,
        minValue: Int,
        maxValue: Int
    ): Int? {
        if (value.isNullOrBlank()) {
            showToast(context, "$fieldName cannot be empty")
            return null
        }

        val number = value.toIntOrNull() ?: run {
            showToast(context, "$fieldName must be a valid number")
            return null
        }

        if (number < minValue || number > maxValue) {
            showToast(context, "$fieldName must be between $minValue and $maxValue")
            return null
        }

        return number
    }

    private fun validateStringField(
        context: Context,
        value: String?,
        fieldName: String
    ): String? {
        return if (value.isNullOrBlank()) {
            showToast(context, "$fieldName cannot be empty")
            null
        } else {
            value
        }
    }

    fun formatDateTime(datetimeStr: String): String {
        val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
        val dateTime = LocalDateTime.parse(datetimeStr, inputFormatter)
        return "${dateTime.toLocalDate()} - ${dateTime.hour}:${dateTime.minute}"
    }

    fun longToLocalDateTime(timestamp: Long): LocalDateTime {
        return Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }

    fun showToast(context: Context, msg: String){
        return Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
    }

    fun formatDateOnly(dateString: String?): String? {
        return try {
            if (dateString == null) return null
            val dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME)
            dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        } catch (e: Exception) {
            null
        }
    }

    fun safeParseIsoDateTime(dateString: String?): LocalDateTime {
        return try {
            LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME)
        } catch (e: Exception) {
            LocalDateTime.now()
        }
    }

    fun centsToUsd(amountInCents: Long): String {
        return "$%.2f".format(amountInCents / 100.0)
    }

}
