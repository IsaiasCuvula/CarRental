package com.bersyte.rent_a_car.features.company.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bersyte.rent_a_car.common.ui.components.CommonSearchBar
import com.bersyte.rent_a_car.common.ui.components.EmptyState
import com.bersyte.rent_a_car.features.auth.viewmodels.AuthViewModel
import com.bersyte.rent_a_car.features.company.data.models.Operator
import com.bersyte.rent_a_car.features.company.ui.components.OperatorCard
import com.bersyte.rent_a_car.features.company.ui.components.StatCard
import com.bersyte.rent_a_car.features.company.viewmodels.CompanyViewModel
import com.bersyte.rent_a_car.utils.enums.UserRole
import com.bersyte.rent_a_car.utils.helpers.AppHelpers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OperatorsScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onAddOperator: () -> Unit,
    viewModel: CompanyViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val allOperatorsState= viewModel.operators.collectAsState()
    val allOperators = allOperatorsState.value

    val authResponse = authViewModel.authResponse.collectAsState()
    val auth = authResponse.value

    var selectedOperator by remember { mutableStateOf<Operator?>(null) }

    LaunchedEffect(Unit) {
        authViewModel.tokenManager.getAuthResponse()
        viewModel.fetchAllOperators(
            onError = { error ->
                AppHelpers.showToast(context, error)
            }
        )
    }

    var searchQuery by remember { mutableStateOf("") }
    val filteredOperators = if (searchQuery.isBlank()) {
        allOperators
    } else {
        allOperators.filter { operator ->
            operator.name.contains(searchQuery, ignoreCase = true) ||
                    operator.email.contains(searchQuery, ignoreCase = true)
        }
    }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Operators", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            val authData = auth?.data

            if( authData != null){
                val role = UserRole.valueOf(authData.role)

                if(role == UserRole.ADMIN){
                    FloatingActionButton(
                        onClick =onAddOperator,
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Operator")
                    }
                }

            }

        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ){
            CommonSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                hintText = "Search operator by name or email",
                onSearch = { focusManager.clearFocus() },
                modifier = Modifier
                    .fillMaxWidth()

            )

            StatCard(
                title = "",
                value = "${allOperators.size}",
                icon = Icons.Default.PeopleAlt,
                modifier = Modifier
                    .fillMaxWidth().padding(vertical =  16.dp)
            )

            if (filteredOperators.isEmpty()) {
                EmptyState(
                    title = "No operators found",
                    message = "Add your first operator by tapping the + button",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(16.dp)
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ){
                    items(filteredOperators) { operator ->
                        OperatorCard(
                            operator = operator,
                            onClick = {
                                val authData = auth?.data

                                if( authData != null){
                                    val role = UserRole.valueOf(authData.role)

                                    if(role == UserRole.ADMIN){
                                        selectedOperator = operator
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }

    selectedOperator?.let { operator ->
        ModalBottomSheet(
            onDismissRequest = {selectedOperator = null},
            sheetState = rememberModalBottomSheetState()
        ) {
            OperatorRentals(operator)
        }
    }
}
