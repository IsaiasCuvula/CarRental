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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.data.models.Resource
import com.bersyte.rent_a_car.common.ui.components.CommonTextField
import com.bersyte.rent_a_car.features.auth.data.models.LoginRequest
import com.bersyte.rent_a_car.features.auth.data.models.SignUpRequest
import com.bersyte.rent_a_car.features.auth.viewmodels.AuthViewModel
import com.bersyte.rent_a_car.utils.enums.UserRole

@Composable
fun AuthScreen(
    onLoginSuccess: (role: UserRole) -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val loginState by viewModel.loginState.collectAsState()

    var isLogin by remember { mutableStateOf(true) }

    val colors = MaterialTheme.colorScheme

    val gradient = Brush.verticalGradient(
        colors = listOf(colors.secondary, colors.primary)
    )

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var cityName by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }

    // Validation function
    fun validate(): Boolean {
        val isEmailValid = email.isNotBlank()
        val isPasswordValid = password.isNotBlank()
        val isRoleValid = isLogin || role.isNotBlank()
        val isCityValid = isLogin || cityName.isNotBlank()

        return isEmailValid && isPasswordValid && isRoleValid && isCityValid
    }

    LaunchedEffect(loginState) {
        val userState = loginState
        if (userState is Resource.Success && userState.data != null) {
            val userRole = UserRole.valueOf(userState.data.role.uppercase())
            onLoginSuccess(userRole)
            viewModel.resetLoginState()
        }
    }

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
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = colors.tertiary
                ),
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
                    )

                    CommonTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        icon = Icons.Default.Email,
                        keyboardType = KeyboardType.Email,
                        isError = email.isBlank()
                    )


                    CommonTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "Password",
                        icon = Icons.Default.Lock,
                        keyboardType = KeyboardType.Password,
                        isPassword = true,
                        isError = email.isBlank()
                    )

                    AnimatedVisibility(visible = !isLogin) {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            CommonTextField(
                                value = role,
                                onValueChange = { role = it },
                                label = "Role",
                                icon = Icons.Default.Person,
                                isError = role.isBlank()
                            )
                            CommonTextField(
                                value = cityName,
                                onValueChange = { cityName = it },
                                label = "City",
                                isError = cityName.isBlank()
                            )
                            CommonTextField(
                                value = street,
                                onValueChange = { street = it },
                                label = "Street",
                                isError = street.isBlank()
                            )
                            CommonTextField(
                                value = state,
                                onValueChange = { state = it },
                                label = "State",
                                isError = state.isBlank()
                            )
                        }
                    }

                    Button(
                        onClick = {
                            if (isLogin) {
                                viewModel.login(
                                    LoginRequest(
                                        email = email,
                                        password = password
                                    )
                                )
                            } else {
                                viewModel.signup(
                                    SignUpRequest(
                                        email = email,
                                        password = password,
                                        role = role.uppercase(),
                                        cityName = cityName
                                    )
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        enabled = validate()
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
