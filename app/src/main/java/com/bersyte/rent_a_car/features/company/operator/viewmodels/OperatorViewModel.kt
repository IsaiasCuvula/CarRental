package com.bersyte.rent_a_car.features.company.operator.viewmodels

import android.util.Log
import com.bersyte.rent_a_car.features.company.admin.data.models.Operator
import com.bersyte.rent_a_car.features.company.operator.data.repositories.OperatorRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerRequest
import com.bersyte.rent_a_car.features.company.operator.data.models.CreateCustomerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class OperatorViewModel @Inject constructor(
    private val repository: OperatorRepository
) : ViewModel() {

    private val _operator = MutableStateFlow<Operator?>(null)
    val operator = _operator.asStateFlow()

    init {
        fetchOperator(onError = {})
    }

    fun createCustomer(
        customer: CreateCustomerRequest,
        onSuccess: (CreateCustomerResponse) -> Unit,
        onError:(String)-> Unit
    ){
        viewModelScope.launch {
            try {
                val response = repository.createCustomer(customer)
                Log.d("CREATE_CUSTOMER", "$response")
                onSuccess(response)
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.d("CREATE_CUSTOMER", "Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.d("CREATE_CUSTOMER", "EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }
        }
    }

    fun fetchOperator(onError:(String)-> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.fetchOperator()
                _operator.value = response
                Log.d("FETCH_OPERATOR", "$response")
            }catch (e: HttpException) {
                val error = e.response()?.errorBody()?.string()
                Log.d("FETCH_OPERATOR", "Error body: $error")
                error?.let { onError(error) }
            } catch (e: Exception) {
                Log.d("FETCH_OPERATOR", "EXCEPTION - $e")
                e.localizedMessage?.let { onError(it) }
            }
        }
    }

}
