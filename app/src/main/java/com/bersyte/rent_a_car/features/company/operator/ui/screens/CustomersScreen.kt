package com.bersyte.rent_a_car.features.company.operator.ui.screens
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bersyte.rent_a_car.common.ui.components.CommonSearchBar
import com.bersyte.rent_a_car.common.ui.components.CustomerCard
import com.bersyte.rent_a_car.common.ui.components.EmptyState
import com.bersyte.rent_a_car.features.customers.profile.data.models.Customer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomersScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onAddCustomer: () -> Unit,
) {
    val customers = listOf<Customer>()
    var searchQuery by remember { mutableStateOf("") }
    val filteredCustomers = if (searchQuery.isBlank()) {
        customers
    } else {
        customers.filter { customer ->
            customer.name.contains(searchQuery, ignoreCase = true) ||
                    customer.email.contains(searchQuery, ignoreCase = true) ||
                    customer.phone.contains(searchQuery, ignoreCase = true)
        }
    }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Customers", fontWeight = FontWeight.Bold) },
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
            FloatingActionButton(
                onClick =onAddCustomer,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Customer")
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            CommonSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                hintText = "Search customer by name or email",
                onSearch = { focusManager.clearFocus() },
                modifier = Modifier
                      .fillMaxWidth()
                      .padding(16.dp)
              )

              if (filteredCustomers.isEmpty()) {
                  EmptyState(
                      modifier = Modifier
                          .fillMaxSize()
                          .weight(1f)
                          .padding(16.dp)
                  )
              } else {
                  LazyColumn(
                      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                      verticalArrangement = Arrangement.spacedBy(12.dp),
                      modifier = Modifier.weight(1f)
                  ){
                          items(filteredCustomers) { customer ->
                              CustomerCard(
                                  customer = customer,
                                  onClick = { /* Handle click if needed */ },
                                  modifier = Modifier.fillMaxWidth()
                              )
                          }
                      }
              }
        }
    }
}
