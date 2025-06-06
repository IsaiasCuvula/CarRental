package com.bersyte.rent_a_car

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bersyte.rent_a_car.common.navigation.CarRentalApp
import com.bersyte.rent_a_car.common.theme.RentACarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentACarTheme {
                CarRentalApp()
            }
        }
    }
}
