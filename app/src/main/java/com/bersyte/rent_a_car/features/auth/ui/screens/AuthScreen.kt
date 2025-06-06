package com.bersyte.rent_a_car.features.auth.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bersyte.rent_a_car.features.auth.ui.components.AuthTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AuthScreen() {
    var isLogin by remember { mutableStateOf(true) }

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF4A00E0), Color(0xFF8E2DE2))
    )

    Surface (modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(24.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(24.dp),
                elevation =  CardDefaults.elevatedCardElevation(4.dp),
               // backgroundColor = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (isLogin) "Login" else "Sign Up",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A00E0)
                    )

                    val email = remember { mutableStateOf("") }
                    val password = remember { mutableStateOf("") }
                    val role = remember { mutableStateOf("") }
                    val cityName = remember { mutableStateOf("") }
                    val street = remember { mutableStateOf("") }
                    val state = remember { mutableStateOf("") }

                    AuthTextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = "Email",
                        icon = Icons.Default.Email,
                        keyboardType = KeyboardType.Email
                    )

                    AuthTextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = "Password",
                        icon = Icons.Default.Lock,
                        keyboardType = KeyboardType.Password,
                        isPassword = true
                    )

                    AnimatedVisibility(visible = !isLogin) {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            AuthTextField(
                                value = role.value,
                                onValueChange = { role.value = it },
                                label = "Role",
                                icon = Icons.Default.Person
                            )
                            AuthTextField(
                                value = cityName.value,
                                onValueChange = { cityName.value = it },
                                label = "City"
                            )
                            AuthTextField(
                                value = street.value,
                                onValueChange = { street.value = it },
                                label = "Street"
                            )
                            AuthTextField(
                                value = state.value,
                                onValueChange = { state.value = it },
                                label = "State"
                            )
                        }
                    }

                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = if (isLogin) "Login" else "Sign Up")
                    }

                    TextButton(onClick = { isLogin = !isLogin }) {
                        Text(
                            text = if (isLogin) "Don't have an account? Sign Up" else "Already have an account? Login",
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}
