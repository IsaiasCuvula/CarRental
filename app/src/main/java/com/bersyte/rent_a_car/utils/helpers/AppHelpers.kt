package com.bersyte.rent_a_car.utils.helpers

import android.content.Context
import android.widget.Toast
import com.bersyte.rent_a_car.features.customers.home.data.models.CarRating
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object AppHelpers{
    fun List<CarRating>.average(selector: (CarRating) -> Float): Float {
        if (isEmpty()) return 0f
        return sumOf { selector(it).toDouble() }.toFloat() / size
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

    fun safeParseIsoDateTime(dateString: String?): LocalDateTime? {
        return try {
            if(dateString == null){
                return null
            }
            LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME)
        } catch (e: Exception) {
            null
        }
    }

    fun centsToUsd(amountInCents: Long): String {
        return "$%.2f".format(amountInCents / 100.0)
    }

}
