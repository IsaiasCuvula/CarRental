package com.bersyte.rent_a_car.utils.helpers

import com.bersyte.rent_a_car.features.customers.home.data.models.CarRating

object AppHelpers{
    fun List<CarRating>.average(selector: (CarRating) -> Float): Float {
        if (isEmpty()) return 0f
        return sumOf { selector(it).toDouble() }.toFloat() / size
    }
}
