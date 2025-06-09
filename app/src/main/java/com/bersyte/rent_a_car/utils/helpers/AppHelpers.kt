package com.bersyte.rent_a_car.utils.helpers

import android.content.Context
import android.widget.Toast
import com.bersyte.rent_a_car.features.customers.home.data.models.CarRating
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object AppHelpers{

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

    fun calculateRoundedRatingAverage(
        ratings: List<CarRating>,
        decimals: Int = 1
    ): Double {
        if (ratings.isEmpty()) return 0.0
        val average = ratings.map { it.rating }.average()
        return "%.${decimals}f".format(average).toDouble()
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
